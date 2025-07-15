package org.example.yeye_backend.domain.video.dto.request;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record CreateVideoRequestDto(
    @NotBlank
    @Length(min = 2, max = 15)
    String title
) {
}
