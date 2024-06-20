package com.bbs.common.web;

import com.bbs.common.service.VerificationCodeService;
import com.bbs.common.vo.DataResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * VerificationCodeController
 *
 * @author yangjiajia
 * @createdAt 2024/4/2 11:42
 */
@RestController
public class VerificationCodeController {

    @Autowired
    private VerificationCodeService verificationCodeService;

    @GetMapping("login-code")
    public DataResult loginCode() {
        return DataResult.success(verificationCodeService.genCode());
    }
}
