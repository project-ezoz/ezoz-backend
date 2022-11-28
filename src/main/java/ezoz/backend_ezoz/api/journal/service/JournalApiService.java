package ezoz.backend_ezoz.api.journal.service;

import ezoz.backend_ezoz.api.journal.dto.JournalDto;
import ezoz.backend_ezoz.api.journal.dto.JournalSearchDto;
import ezoz.backend_ezoz.domain.journal.entity.Journal;
import ezoz.backend_ezoz.domain.journal.entity.JournalImage;
import ezoz.backend_ezoz.domain.journal.entity.JournalType;
import ezoz.backend_ezoz.domain.journal.service.JournalService;
import ezoz.backend_ezoz.global.error.exception.BusinessException;
import ezoz.backend_ezoz.global.error.exception.ErrorCode;
import ezoz.backend_ezoz.global.jwt.TokenManager;
import ezoz.backend_ezoz.infra.FileService;
import ezoz.backend_ezoz.infra.UploadFile;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JournalApiService {

    private final JournalService journalService;
    private final FileService fileService;
    private final TokenManager tokenManager;

    public Long registerJournal(JournalDto.Request journalRequestDto) {

        List<JournalImage> journalImages = new ArrayList<>();
        registerJournalImages(journalImages, journalRequestDto.getJournalImageFiles());

        Journal journal = journalRequestDto.toEntity("ckdgus", journalImages);

        return journalService.save(journal);
    }

    private void registerJournalImages(List<JournalImage> journalImages, List<MultipartFile> multipartFiles) {

        for (MultipartFile multipartFile : multipartFiles) {
            try {
                UploadFile uploadFile = fileService.storeFile(multipartFile);
                journalImages.add(uploadFile.createJournalImage());
            } catch (IOException e) {
                throw new BusinessException(ErrorCode.FAILED_REGISTER_IMAGE);
            }
        }
    }

    public List<JournalDto.Response> searchByKeywordWithPaging(String keyword, String type, int page) {

        List<Journal> journalList =
                journalService.searchByKeywordWithPaging(keyword, JournalType.from(type), page);

        List<JournalDto.Response> journalResponseList = new ArrayList<>();
        for (Journal journal : journalList) {
            String fileKey = journal.getJournalImages().get(0).getStoreFileName();
            journalResponseList.add(JournalDto.Response.of(journal.getJournalId(), journal.getTitle(), fileKey));
        }

        return journalResponseList;
    }

}
