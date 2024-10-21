package com.wooxher.portfolio.presentation.controller

 import com.fasterxml.jackson.databind.ObjectMapper
 import com.wooxher.portfolio.domain.entity.Member
 import com.wooxher.portfolio.domain.entity.Pet
 import com.wooxher.portfolio.domain.repository.MemberRepository
 import org.assertj.core.api.Assertions.*
 import org.json.JSONArray
 import org.json.JSONObject
 import org.junit.jupiter.api.*
 import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
 import org.springframework.http.MediaType
 import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.MvcResult
 import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder
 import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
 import org.springframework.test.web.servlet.result.MockMvcResultMatchers
 import java.nio.charset.StandardCharsets
 import java.time.LocalDate
 import kotlin.test.Test
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("[API 컨트롤러 테스트]")
class PresentationControllerTest(
    @Autowired private val mockMvc : MockMvc,
    @Autowired private val memberRepository: MemberRepository,
    @Autowired private val mapper : ObjectMapper,
){

    val DATA_SIZE = 3

    // 더미 엔티티 생성
    private fun createMember(n: Int): Member {

        val member = Member(
            name = "${n}",
            age = n,
            address = "서울",
            birthday = LocalDate.now(),
        )

        val pets = mutableListOf<Pet>()
        for(i in 1..n){
            val memberPets = Pet(name = "${n}", age = n, breed = "${n+1}",)
            pets.add(memberPets)
        }
        member.addMemberPet(pets)

        return member
    }

    @BeforeAll
    fun beforeEach(){
//        println("데이터 초기화 이전 조회 시작")
//        val beforeInit = memberRepository.findAll()
//        assertThat(beforeInit).hasSize(0)
//        println("데이터 초기화 이전 조회 종료")

        println("데이터 초기화 시작")
        val members = mutableListOf<Member>()
        for(i in 1..DATA_SIZE){
            val createMember = createMember(i)
            members.add(createMember)
        }
        memberRepository.saveAll(members)
        println("데이터 초기화 종료")
    }

    @AfterAll
    fun afterEach(){
        println("데이터베이스 초기화 시작")
        memberRepository.deleteAll()
        println("데이터베이스 초기화 종료")
    }
    @Test
    @DisplayName("멤버조회")
    fun testGetMember(){
        println("멤버조회 시작")
        val id: Long = 1
        //given
        val uri ="/api/v1/member/${id}"

        //when
        val result = performGet(uri)
        val contentAsString = result.response.getContentAsString((StandardCharsets.UTF_8))
        println("contentAsString: ${contentAsString}")
        // json으로
        val jsonObject =JSONObject(contentAsString)

        //then
        assertThat(jsonObject.length()).isPositive()
    }

    @Test
    @DisplayName("반려동물조회")
    fun testGetPets(){

        val memberId: Long = 1
        //given
        val uri ="/api/v1/pets/${memberId}"

        //when
        val result = performGet(uri, memberId)
        val contentAsString = result.response.getContentAsString((StandardCharsets.UTF_8))
        println("contentAsString: ${contentAsString}")
        // json으로
        val jsonArray =JSONArray(contentAsString)

        //then
        assertThat(jsonArray.length()).isPositive()
    }

    @Test
    @DisplayName("특정 멤버의 펫 조회")
    fun getMemberPet(){
        val memberId:Long = 1
        val petId:Long = 1
        //given
        val uri = "/api/v1/pet/$memberId/$petId"

        //when
        val result = performGet(uri)
        val contentAsString = result.response.getContentAsString(StandardCharsets.UTF_8)
        val jsonObject = JSONObject(contentAsString)

        //then
        assertThat(jsonObject.length()).isPositive()
    }

    @Test
    @DisplayName("회원추가-정상")
    fun addMember() {

        val uri = "/api/v1/member"

        val member = Member("테스터", 20, "테스트주소", LocalDate.now())
        val json = mapper.writeValueAsString(member)

        val result = performPost(uri, json)
        val contentAsString = result.response.getContentAsString(StandardCharsets.UTF_8)
        val jsonObject = JSONObject(contentAsString)

        assertThat(jsonObject.length()).isPositive()
    }
    @Test
    @DisplayName("회원추가-잘못된 입력")
    fun addMemberBadInput() {

        val uri = "/api/v1/member"

        val json = """
            {
                "name": "테스터",
                "age": "잘못된 입력"
            }
        """.trimIndent()

        performPostBadRequest(uri, json)
    }

    @Test
    @DisplayName("반려동물추가")
    fun addPet() {
        val memberId = 1

        val uri = "/api/v1/pets/$memberId"

        val pet = listOf(Pet("테스터펫", 2, "테스트종"))
        val json = mapper.writeValueAsString(pet)

        val result = performPost(uri, json)
        val contentAsString = result.response.getContentAsString(StandardCharsets.UTF_8)
        val jsonObject = JSONObject(contentAsString)

        assertThat(jsonObject.length()).isPositive()
    }

    @Test
    @DisplayName("반려동물추가- 잘못된 입력")
    fun addPetBadInput() {
        val memberId = 1

        val uri = "/api/v1/pets/$memberId"

        val json = """
            {
                "name": "테스터펫",
                "age": "잘못된 입력"
            }
        """.trimIndent()

        performPostBadRequest(uri, json)
    }

    @Test
    @DisplayName("회원수정")
    fun updateMember() {
        val memberId = 1

        val uri = "/api/v1/member/$memberId"

        val member = Member("업데이트", 55, "업데이트주소", LocalDate.now())
        val json = mapper.writeValueAsString(member)

        val result = performPut(uri, json)
        val contentAsString = result.response.getContentAsString(StandardCharsets.UTF_8)
        val jsonObject = JSONObject(contentAsString)

        assertThat(jsonObject.length()).isPositive()
    }

    @Test
    @DisplayName("반려동물 수정")
    fun updatePet() {
        val memberId = 1
        val petId = 1

        val uri = "/api/v1/pet/$memberId/$petId"

        val pet = Pet("업데이트펫", 5, "업데이트종",)
        val json = mapper.writeValueAsString(pet)

        val result = performPut(uri, json)
        val contentAsString = result.response.getContentAsString(StandardCharsets.UTF_8)
        val jsonObject = JSONObject(contentAsString)

        assertThat(jsonObject.length()).isPositive()
    }

    @Test
    @DisplayName("회원탈퇴")
    fun deleteMember() {
        val memberId = 1

        val uri = "/api/v1/member/$memberId"

        performDelete(uri)
    }

    @Test
    @DisplayName("반려동물 삭제")
    fun deletePet() {
        val memberId = 1
        val petId = 1
        val uri = "/api/v1/pet/$memberId/$petId"

        performDelete(uri)
    }

    private fun performGet(uri: String): MvcResult {
        return mockMvc
            .perform(MockMvcRequestBuilders.get(uri))
            .andDo(MockMvcResultHandlers.print())
            .andReturn()
    }

    private fun performGet(uri: String, memberId: Long): MvcResult {
        return mockMvc
            .perform(MockMvcRequestBuilders.get(uri).
                requestAttr("memberId", memberId)
                .contentType(MediaType.APPLICATION_JSON))
            .andDo(MockMvcResultHandlers.print())
            .andReturn()
    }

    private fun performPost(uri: String, json: String): MvcResult {
        return mockMvc
            .perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
            .andDo(MockMvcResultHandlers.print())
            .andReturn()
    }

    private fun performPostBadRequest(uri: String, json: String): MvcResult {
        return mockMvc
            .perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isBadRequest)
            .andReturn()
    }


    private fun performPut(uri: String, json: String): MvcResult {
        return mockMvc
            .perform(MockMvcRequestBuilders.put(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
            .andDo(MockMvcResultHandlers.print())
            .andReturn()
    }

    private fun performDelete(uri: String,): MvcResult {
        return mockMvc
            .perform(MockMvcRequestBuilders.delete(uri))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andReturn()
    }

}