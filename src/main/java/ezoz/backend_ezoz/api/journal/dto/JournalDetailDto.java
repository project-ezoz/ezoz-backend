package ezoz.backend_ezoz.api.journal.dto;

import ezoz.backend_ezoz.domain.journal.entity.Journal;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
@ApiModel(value = "저널 조회 API 응답 값", description = "저널 조회에 따른 응답 값입니다.")
public class JournalDetailDto {

    @ApiModelProperty(value = "저널id", example = "1")
    private Long journalId;

    @ApiModelProperty(value = "저널 제목", example = "무언가를 두고 오고 싶을 때 가고 싶은 곳")
    private String title;

    @ApiModelProperty(value = "저널 내용", example = "Lorem ipsum dolor sit amet...")
    private String content;

    @ApiModelProperty(value = "저널 저자", example = "전성은")
    private String author;

    @ApiModelProperty(value = "S3의 해당 저널 이미지 key 값이 담긴 리스트")
    private List<String> journalImageUrls;

    public static JournalDetailDto of(Journal journal, List<String> journalImageUrls) {

        return JournalDetailDto.builder()
                .journalId(journal.getJournalId())
                .title(journal.getTitle())
                .content(journal.getContent())
                .author(journal.getAuthor())
                .journalImageUrls(journalImageUrls)
                .build();
    }



}
