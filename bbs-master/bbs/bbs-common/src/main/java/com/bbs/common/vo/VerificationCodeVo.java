package com.bbs.common.vo;

import lombok.Builder;
import lombok.Data;

/**
 * VerificationCodeVo
 *
 * @author yangjiajia
 * @createdAt 2024/4/2 11:40
 */
@Data
@Builder
public class VerificationCodeVo {

    private String codeId;

    private String image;
}
