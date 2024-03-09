package net.protsenko.shortLinkService.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShortLink {
    private String code;
    private String path;
}
