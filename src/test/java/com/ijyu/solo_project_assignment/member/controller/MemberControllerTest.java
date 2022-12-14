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
    @DisplayName("?????? ?????? ?????????")
    void postUser() throws Exception {
        // given
        String name = "?????????", companyName = "????????? ??????", password = "????????? ??????", sex = "m";
        Long companyLocationId = 2L, companyTypeId = 11L, memberId = 11L;
        String companyLocationName = "??????", companyTypeName = "????????????";
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
                                        fieldWithPath("name").type(JsonFieldType.STRING).description("?????? ??????"),
                                        fieldWithPath("password").type(JsonFieldType.STRING).description("????????????"),
                                        fieldWithPath("sex").type(JsonFieldType.STRING).description("??????"),
                                        fieldWithPath("companyName").type(JsonFieldType.STRING).description("?????????"),
                                        fieldWithPath("companyTypeId").type(JsonFieldType.NUMBER).description("?????? ?????? ?????????"),
                                        fieldWithPath("companyLocationId").type(JsonFieldType.NUMBER).description("?????? ?????? ?????????")
                                )
                        ),
                        responseFields(
                                fieldWithPath("data").description("?????????"))
                                .andWithPrefix("data.",
                                        List.of(
                                                fieldWithPath("memberId").type(JsonFieldType.NUMBER).description("?????? ?????????"),
                                                fieldWithPath("name").type(JsonFieldType.STRING).description("?????? ??????"),
                                                fieldWithPath("sex").type(JsonFieldType.STRING).description("??????"),
                                                fieldWithPath("companyName").type(JsonFieldType.STRING).description("?????????"),
                                                fieldWithPath("companyTypeName").type(JsonFieldType.STRING).description("?????? ??????"),
                                                fieldWithPath("companyLocationName").type(JsonFieldType.STRING).description("?????? ??????")
                                        )
                                )
                        )
                );
    }

    @Test
    @DisplayName("?????? ?????? ?????????")
    void patchUser() throws Exception {
        // given
        String name = "?????????", password = "????????? ??????", sex = "f", companyName = "????????? ??????";
        Long memberId = 1L, companyTypeId = 2L, companyLocationId = 3L;
        String companyTypeName = "??????", companyLocationName = "??????";
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
                                        fieldWithPath("memberId").type(JsonFieldType.NUMBER).description("?????? ?????????").ignored(),
                                        fieldWithPath("name").type(JsonFieldType.STRING).description("?????? ??????").optional(),
                                        fieldWithPath("password").type(JsonFieldType.STRING).description("????????????").optional(),
                                        fieldWithPath("sex").type(JsonFieldType.STRING).description("??????").optional(),
                                        fieldWithPath("companyName").type(JsonFieldType.STRING).description("?????????").optional(),
                                        fieldWithPath("companyTypeId").type(JsonFieldType.NUMBER).description("?????? ?????? ?????????").optional(),
                                        fieldWithPath("companyLocationId").type(JsonFieldType.NUMBER).description("?????? ?????? ?????????").optional()
                                )
                        ),
                        responseFields(
                                fieldWithPath("data").description("?????????"))
                                .andWithPrefix("data.",
                                        List.of(
                                                fieldWithPath("memberId").type(JsonFieldType.NUMBER).description("?????? ?????????"),
                                                fieldWithPath("name").type(JsonFieldType.STRING).description("?????? ??????"),
                                                fieldWithPath("sex").type(JsonFieldType.STRING).description("??????"),
                                                fieldWithPath("companyName").type(JsonFieldType.STRING).description("?????????"),
                                                fieldWithPath("companyTypeName").type(JsonFieldType.STRING).description("?????? ??????"),
                                                fieldWithPath("companyLocationName").type(JsonFieldType.STRING).description("?????? ??????")
                                        )
                                )
                        )
                );
    }

    @Test
    @DisplayName("?????? ?????? ?????? - ?????????")
    void getUsersPaging() throws Exception {
        // given
        String size = "2", page = "1";
        List<CompanyType> companyTypeList = List.of(
                CompanyType.builder().companyTypeId(11L).companyTypeName("????????????").build(),
                CompanyType.builder().companyTypeId(14L).companyTypeName("?????????").build()
        );
        List<CompanyLocation> companyLocationList = List.of(
                CompanyLocation.builder().companyLocationId(1L).companyLocationName("??????").build(),
                CompanyLocation.builder().companyLocationId(7L).companyLocationName("??????").build()
        );
        List<Member> memberList = List.of(
                Member.builder()
                        .memberId(10L)
                        .companyName("?????? ????????????")
                        .name("?????????9")
                        .password("6gEsvxGEa")
                        .sex("f")
                        .companyType(companyTypeList.get(0))
                        .companyLocation(companyLocationList.get(0))
                        .build(),
                Member.builder()
                        .memberId(9L)
                        .companyName("?????? ?????????")
                        .name("?????????8")
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
                        parameterWithName("page").description("????????? ?????????"),
                        parameterWithName("size").description("????????? ??? ????????? ????????? ??????")
                ),
                responseFields(
                        List.of(
                                fieldWithPath("data").description("?????????"),
                                fieldWithPath("pageInfo").description("????????? ??????")
                        )
                ).andWithPrefix("data[].",
                        List.of(
                                fieldWithPath("memberId").type(JsonFieldType.NUMBER).description("?????? ?????????"),
                                fieldWithPath("name").type(JsonFieldType.STRING).description("?????? ??????"),
                                fieldWithPath("sex").type(JsonFieldType.STRING).description("??????"),
                                fieldWithPath("companyName").type(JsonFieldType.STRING).description("?????????"),
                                fieldWithPath("companyTypeName").type(JsonFieldType.STRING).description("?????? ??????"),
                                fieldWithPath("companyLocationName").type(JsonFieldType.STRING).description("?????? ??????")
                        )
                ).andWithPrefix("pageInfo.",
                        List.of(
                                fieldWithPath("page").type(JsonFieldType.NUMBER).description("?????? ?????????"),
                                fieldWithPath("size").type(JsonFieldType.NUMBER).description("????????? ??? ????????? ????????? ??????"),
                                fieldWithPath("totalElements").type(JsonFieldType.NUMBER).description("????????? ??? ??????"),
                                fieldWithPath("totalPages").type(JsonFieldType.NUMBER).description("????????? ??? ??????")
                        )
                )
        ));
    }

    @Test
    @DisplayName("?????? ?????? ?????? - ?????? : Type")
    void getUsersFilterType() {
        // given

        // when

        // then
    }

    @Test
    @DisplayName("?????? ?????? ?????? - ?????? : Location")
    void getUsersFilterLocation() {
        // given

        // when

        // then
    }

    @Test
    @DisplayName("?????? ?????? ?????? - ?????? : Type & Location")
    void getUsersFilterTypeAndLocation() {
        // given

        // when

        // then
    }


    @Test
    @DisplayName("?????? ?????? ??????")
    void getUser() {
        // given

        // when

        // then
    }

    @Test
    @DisplayName("?????? ??????")
    void deleteUser() {
        // given

        // when

        // then
    }
}