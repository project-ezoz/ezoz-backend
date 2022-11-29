package ezoz.backend_ezoz.api.journal.dto;

import ezoz.backend_ezoz.domain.journal.entity.Journal;
import ezoz.backend_ezoz.domain.journal.entity.JournalImage;
import ezoz.backend_ezoz.domain.journal.entity.JournalType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import java.util.List;

public class JournalDto {
    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @ApiModel(value = "저널 생성 API 요청 값", description = "저널 생성에 필요한 요청 값입니다.")
    public static class Request {

        @NotBlank
        @ApiModelProperty(value = "저널타입 (EZ or OZ)", required = true)
        private String journalType;

        @NotBlank
        @ApiModelProperty(value = "저널 제목", required = true)
        private String title;

        @NotBlank
        @ApiModelProperty(value = "저널 내용", required = true)
        private String content;

        @ApiModelProperty(value = "multipartFile 형식의 저널 이미지들", required = true)
        private List<MultipartFile> journalImageFiles;

        public Journal toEntity(String author, List<JournalImage> journalImages) {
            return Journal.builder()
                    .journalType(JournalType.from(journalType))
                    .title(title)
                    .content(content)
                    .author(author)
                    .journalImages(journalImages)
                    .build();
        }

    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @ApiModel(value = "저널 검색 API 응답 값", description = "저널 검색에 따른 응답 값들입니다.")
    public static class Response {

        @ApiModelProperty(value = "저널 id", example = "1")
        private Long journalId;


        @ApiModelProperty(value = "저널 제목", example = "무언가를 두고 오고 싶을 때 가고 싶은 곳")
        private String title;


        @ApiModelProperty(value = "S3의 해당 저널 이미지 key 값이 담긴 리스트")
        private String JournalImageUrl;

        public static JournalDto.Response of(Long journalId, String title, String journalImageUrl) {
            return Response.builder()
                    .journalId(journalId)
                    .title(title)
                    .JournalImageUrl(journalImageUrl)
                    .build();
        }

    }
}
