package com.ijyu.solo_project_assignment.member.controller;

import com.google.gson.Gson;
import com.ijyu.solo_project_assignment.company_location.entity.CompanyLocation;
import com.ijyu.solo_project_assignment.company_location.service.CompanyLocationService;
import com.ijyu.solo_project_assignment.company_type.entity.CompanyType;
import com.ijyu.solo_project_assignment.company_type.service.CompanyTypeService;
import com.ijyu.solo_project_assignment.member.dto.MemberPatchDto;
import com.ijyu.solo_project_assignment.member.dto.MemberPostDto;
import com.ijyu.solo_project_assignment.member.dto.MemberResponseDto;
import com.ijyu.solo_project_assignment.member.entity.Member;
import com.ijyu.solo_project_assignment.member.mapper.MemberMapper;
import com.ijyu.solo_project_assignment.member.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@WebMvcTest
//@MockBean(JpaMetamodelMappingContext.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
class MemberControllerTest {
    @Autowired
    private MockMvc mockMvc;

//    @MockBean
//    private MemberService memberService;
//
//    @MockBean
//    private MemberMapper memberMapper;
//
//    @MockBean
//    private CompanyLocationService companyLocationService;
//
//    @MockBean
//    private CompanyTypeService companyTypeService;

    @Autowired
    private Gson gson;

    @Test
    @DisplayName("회원 등록 테스트")
    void postUser() throws Exception {
        // given
        String name = "테스트", companyName = "테스트 회사", password = "테스트 암호", sex = "m";
        Long companyLocationId = 2L, companyTypeId = 11L, memberId = 11L;
        String companyLocationName = "경기", companyTypeName = "정보통신";
        MemberPostDto memberPostDto = MemberPostDto.builder()
                .name(name)
                .companyName(companyName)
                .password(password)
                .sex(sex)
                .companyLocationId(companyLocationId)
                .companyTypeId(companyTypeId)
                .build();
        String content = gson.toJson(memberPostDto);

//        MemberResponseDto memberResponseDto = MemberResponseDto.builder()
//                .memberId(memberId)
//                .companyLocationName(companyLocationName)
//                .sex(sex)
//                .companyTypeName(companyTypeName)
//                .name(name)
//                .companyName(companyName)
//                .build();
//
//        given(memberMapper.memberPostDtoToMember(Mockito.any(MemberPostDto.class), Mockito.any(CompanyType.class), Mockito.any(CompanyLocation.class))).willReturn(new Member());
//        given(companyTypeService.findCompanyType(Mockito.any(Long.class))).willReturn(new CompanyType());
//        given(companyLocationService.findCompanyLocation(Mockito.any(Long.class))).willReturn(new CompanyLocation());
//        given(memberService.createUser(Mockito.any(Member.class))).willReturn(new Member());
//        given(memberMapper.memberToMemberResponseDto(Mockito.any(Member.class))).willReturn(memberResponseDto);

        // when
        ResultActions actions = mockMvc.perform(
                post("/v1/members")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content)
        );

        // then
        actions.andExpect(status().isCreated())
                .andExpect(jsonPath("$.data.name").value(name))
                .andExpect(jsonPath("$.data.companyName").value(companyName))
                .andExpect(jsonPath("$.data.sex").value(sex))
                .andExpect(jsonPath("$.data.memberId").value(memberId))
                .andExpect(jsonPath("$.data.companyTypeName").value(companyTypeName))
                .andExpect(jsonPath("$.data.companyLocationName").value(companyLocationName))
                .andDo(document(
                        "post-member",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestFields(
                                List.of(
                                        fieldWithPath("name").type(JsonFieldType.STRING).description("회원 이름"),
                                        fieldWithPath("password").type(JsonFieldType.STRING).description("비밀번호"),
                                        fieldWithPath("sex").type(JsonFieldType.STRING).description("성별"),
                                        fieldWithPath("companyName").type(JsonFieldType.STRING).description("회사명"),
                                        fieldWithPath("companyTypeId").type(JsonFieldType.NUMBER).description("회사 타입 식별자"),
                                        fieldWithPath("companyLocationId").type(JsonFieldType.NUMBER).description("회사 지역 식별자")
                                )
                        ),
                        responseFields(
                                fieldWithPath("data").description("데이터"))
                                .andWithPrefix("data.",
                                        List.of(
                                                fieldWithPath("memberId").type(JsonFieldType.NUMBER).description("회워 식별자"),
                                                fieldWithPath("name").type(JsonFieldType.STRING).description("회원 이름"),
                                                fieldWithPath("sex").type(JsonFieldType.STRING).description("성별"),
                                                fieldWithPath("companyName").type(JsonFieldType.STRING).description("회사명"),
                                                fieldWithPath("companyTypeName").type(JsonFieldType.STRING).description("회사 타입"),
                                                fieldWithPath("companyLocationName").type(JsonFieldType.STRING).description("회사 지역")
                                        )
                                )
                        )
                );
    }

    @Test
    @DisplayName("회원 수정 테스트")
    void patchUser() throws Exception {
        // given
        String name = "테스트", password = "테스트 암호", sex = "f", companyName = "테스트 회사";
        Long memberId = 1L, companyTypeId = 2L, companyLocationId = 3L;
        String companyTypeName = "어업", companyLocationName = "인천";
        MemberPatchDto memberPatchDto = MemberPatchDto.builder()
                .memberId(memberId)
                .name(name)
                .password(password)
                .sex(sex)
                .companyName(companyName)
                .companyTypeId(companyTypeId)
                .companyLocationId(companyLocationId)
                .build();
        String content = gson.toJson(memberPatchDto);

//        MemberResponseDto memberResponseDto = MemberResponseDto.builder()
//                .memberId(memberId)
//                .name(name)
//                .companyName(companyName)
//                .sex(sex)
//                .companyTypeName(companyTypeName)
//                .companyLocationName(companyLocationName)
//                .build();

//        given(memberMapper.memberPatchDtoToMember(Mockito.any(MemberPatchDto.class),
//                Mockito.any(CompanyType.class),
//                Mockito.any(CompanyLocation.class))).willReturn(new Member());
//        given(companyTypeService.findCompanyType(Mockito.any(Long.class))).willReturn(new CompanyType());
//        given(companyLocationService.findCompanyLocation(Mockito.any(Long.class))).willReturn(new CompanyLocation());
//        given(memberService.patchUser(Mockito.any(Member.class))).willReturn(new Member());
//        given(memberMapper.memberToMemberResponseDto(Mockito.any(Member.class))).willReturn(memberResponseDto);

        // when
        ResultActions actions = mockMvc.perform(
                patch("/v1/members/{member-id}", memberId)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content)
        );

        // then
        actions.andExpect(status().isOk())
                .andExpect(jsonPath("$.data.memberId").value(memberId))
                .andExpect(jsonPath("$.data.companyName").value(companyName))
                .andExpect(jsonPath("$.data.sex").value(sex))
                .andExpect(jsonPath("$.data.memberId").value(memberId))
                .andExpect(jsonPath("$.data.companyTypeName").value(companyTypeName))
                .andExpect(jsonPath("$.data.companyLocationName").value(companyLocationName))
                .andDo(document(
                        "patch-members",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestFields(
                                List.of(
                                        fieldWithPath("memberId").type(JsonFieldType.NUMBER).description("회원 식별자").ignored(),
                                        fieldWithPath("name").type(JsonFieldType.STRING).description("회원 이름").optional(),
                                        fieldWithPath("password").type(JsonFieldType.STRING).description("비밀번호").optional(),
                                        fieldWithPath("sex").type(JsonFieldType.STRING).description("성별").optional(),
                                        fieldWithPath("companyName").type(JsonFieldType.STRING).description("회사명").optional(),
                                        fieldWithPath("companyTypeId").type(JsonFieldType.NUMBER).description("회사 타입 식별자").optional(),
                                        fieldWithPath("companyLocationId").type(JsonFieldType.NUMBER).description("회사 지역 식별자").optional()
                                )
                        ),
                        responseFields(
                                fieldWithPath("data").description("데이터"))
                                .andWithPrefix("data.",
                                        List.of(
                                                fieldWithPath("memberId").type(JsonFieldType.NUMBER).description("회워 식별자"),
                                                fieldWithPath("name").type(JsonFieldType.STRING).description("회원 이름"),
                                                fieldWithPath("sex").type(JsonFieldType.STRING).description("성별"),
                                                fieldWithPath("companyName").type(JsonFieldType.STRING).description("회사명"),
                                                fieldWithPath("companyTypeName").type(JsonFieldType.STRING).description("회사 타입"),
                                                fieldWithPath("companyLocationName").type(JsonFieldType.STRING).description("회사 지역")
                                        )
                                )
                        )
                );
    }

    @Test
    @DisplayName("회원 전체 조회 - 페이징")
    void getUsersPaging() throws Exception {
        // given
        String size = "2", page = "1";
        List<CompanyType> companyTypeList = List.of(
                CompanyType.builder().companyTypeId(11L).companyTypeName("정보통신").build(),
                CompanyType.builder().companyTypeId(14L).companyTypeName("부동산").build()
        );
        List<CompanyLocation> companyLocationList = List.of(
                CompanyLocation.builder().companyLocationId(1L).companyLocationName("서울").build(),
                CompanyLocation.builder().companyLocationId(7L).companyLocationName("대전").build()
        );
        List<Member> memberList = List.of(
                Member.builder()
                        .memberId(10L)
                        .companyName("서울 정보통신")
                        .name("홍길동9")
                        .password("6gEsvxGEa")
                        .sex("f")
                        .companyType(companyTypeList.get(0))
                        .companyLocation(companyLocationList.get(0))
                        .build(),
                Member.builder()
                        .memberId(9L)
                        .companyName("대전 부동산")
                        .name("홍길동8")
                        .password("8eX6wSgQw")
                        .sex("f")
                        .companyType(companyTypeList.get(1))
                        .companyLocation(companyLocationList.get(1))
                        .build()
        );
        PageRequest pageRequest = PageRequest.of(Integer.parseInt(page) - 1, Integer.parseInt(size));
        int start = (int) pageRequest.getOffset();
        int end = Math.min((start + pageRequest.getPageSize()), memberList.size());
        Page<Member> memberPage = new PageImpl<>(memberList.subList(start, end), pageRequest, memberList.size());

        // when
        ResultActions actions = mockMvc.perform(
                get("/v1/members")
                        .param("size", size)
                        .param("page", page)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
        );

        // then
        actions.andExpectAll(
                status().isOk(),
                jsonPath("$.data[0].memberId").value(memberList.get(0).getMemberId()),
                jsonPath("$.data[0].name").value(memberList.get(0).getName()),
                jsonPath("$.data[0].sex").value(memberList.get(0).getSex()),
                jsonPath("$.data[0].companyName").value(memberList.get(0).getCompanyName()),
                jsonPath("$.data[0].companyTypeName").value(companyTypeList.get(0).getCompanyTypeName()),
                jsonPath("$.data[0].companyLocationName").value(companyLocationList.get(0).getCompanyLocationName()),

                jsonPath("$.data[1].memberId").value(memberList.get(1).getMemberId()),
                jsonPath("$.data[1].name").value(memberList.get(1).getName()),
                jsonPath("$.data[1].sex").value(memberList.get(1).getSex()),
                jsonPath("$.data[1].companyName").value(memberList.get(1).getCompanyName()),
                jsonPath("$.data[1].companyTypeName").value(companyTypeList.get(1).getCompanyTypeName()),
                jsonPath("$.data[1].companyLocationName").value(companyLocationList.get(1).getCompanyLocationName()),

                jsonPath("$.pageInfo.page").value(pageRequest.getPageNumber() + 1),
                jsonPath("$.pageInfo.size").value(pageRequest.getPageSize())
        ).andDo(document(
                "get-members-paging",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                requestParameters(
                        parameterWithName("page").description("표현할 페이지"),
                        parameterWithName("size").description("페이지 당 표현할 데이터 개수")
                ),
                responseFields(
                        List.of(
                                fieldWithPath("data").description("데이터"),
                                fieldWithPath("pageInfo").description("페이징 정보")
                        )
                ).andWithPrefix("data[].",
                        List.of(
                                fieldWithPath("memberId").type(JsonFieldType.NUMBER).description("회워 식별자"),
                                fieldWithPath("name").type(JsonFieldType.STRING).description("회원 이름"),
                                fieldWithPath("sex").type(JsonFieldType.STRING).description("성별"),
                                fieldWithPath("companyName").type(JsonFieldType.STRING).description("회사명"),
                                fieldWithPath("companyTypeName").type(JsonFieldType.STRING).description("회사 타입"),
                                fieldWithPath("companyLocationName").type(JsonFieldType.STRING).description("회사 지역")
                        )
                ).andWithPrefix("pageInfo.",
                        List.of(
                                fieldWithPath("page").type(JsonFieldType.NUMBER).description("현재 페이지"),
                                fieldWithPath("size").type(JsonFieldType.NUMBER).description("페이지 당 표현할 데이터 개수"),
                                fieldWithPath("totalElements").type(JsonFieldType.NUMBER).description("데이터 총 개수"),
                                fieldWithPath("totalPages").type(JsonFieldType.NUMBER).description("페이지 총 개수")
                        )
                )
        ));
    }

    @Test
    @DisplayName("회원 전체 조회 - 필터 : Type")
    void getUsersFilterType() {
        // given

        // when

        // then
    }

    @Test
    @DisplayName("회원 전체 조회 - 필터 : Location")
    void getUsersFilterLocation() {
        // given

        // when

        // then
    }

    @Test
    @DisplayName("회원 전체 조회 - 필터 : Type & Location")
    void getUsersFilterTypeAndLocation() {
        // given

        // when

        // then
    }


    @Test
    @DisplayName("특정 회원 조회")
    void getUser() {
        // given

        // when

        // then
    }

    @Test
    @DisplayName("회원 삭제")
    void deleteUser() {
        // given

        // when

        // then
    }
}