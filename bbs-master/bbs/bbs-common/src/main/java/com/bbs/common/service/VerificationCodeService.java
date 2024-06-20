package com.bbs.common.service;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.core.lang.UUID;
import com.bbs.common.vo.VerificationCodeVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@Service
public class VerificationCodeService {

    private Map<String, String> codeMap = new ConcurrentHashMap<>();

    public VerificationCodeVo genCode() {
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(100, 30, 4, 1);
        String code = lineCaptcha.getCode();
        String codeImage = lineCaptcha.getImageBase64Data();
        String codeId = UUID.fastUUID().toString();

        codeMap.put(codeId, code);

        return VerificationCodeVo.builder()
                .codeId(codeId)
                .image(codeImage)
                .build();
    }

    public boolean checkCode(String codeId, String code) {
        String codeInfo = codeMap.get(codeId);
        if (StringUtils.isBlank(codeInfo)) {
            return false;
        }
        if (code.equalsIgnoreCase(codeInfo)) {
            return true;
        }
        return false;
    }
}
