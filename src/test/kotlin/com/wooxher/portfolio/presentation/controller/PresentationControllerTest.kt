package com.wooxher.portfolio.presentation.controller

 import com.wooxher.portfolio.domain.entity.Member
 import com.wooxher.portfolio.domain.entity.Pet
 import com.wooxher.portfolio.domain.repository.MemberRepository
 import com.wooxher.portfolio.domain.repository.PetRepository
 import org.assertj.core.api.Assertions
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
 import org.springframework.transaction.annotation.Transactional
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
    @Autowired private val petRepository: PetRepository,
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
            val memberPets = Pet(name = "${n}", age = n, breed = "${n+1}", member = member)
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
        val uri ="/api/v1/pets"

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


}