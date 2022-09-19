package org.deco.gachicoding.file.presentation;

import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.deco.gachicoding.file.application.FileService;
import org.deco.gachicoding.file.presentation.dto.request.FileSaveRequest;
import org.deco.gachicoding.file.presentation.dto.request.FileValid;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Api(tags = "파일 처리 API")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class RestFileController {
    private final FileService fileService;

    @ApiOperation(value = "파일 임시 저장")
    @ApiResponses(
            @ApiResponse(code = 200, message = "임시 폴더의 파일 URL 반환")
    )
    @PostMapping("/file/upload")
    public List<String> fileUploadImageFile(@ApiParam(value = "멀티파트 파일") @FileValid FileSaveRequest request) throws IOException {

        System.out.println(request.getFiles().get(0).getOriginalFilename());

        return null;
//        return fileService.uploadTempImg(files);
    }
}
