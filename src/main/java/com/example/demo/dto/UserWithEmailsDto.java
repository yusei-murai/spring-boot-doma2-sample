package com.example.demo.dto;

import java.util.ArrayList;
import java.util.List;

public record UserWithEmailsDto(
    Long id,
    List<EmailDto> emails
) {
    public record EmailDto(
        Long id,
        String mail
    ) {}
    
    public static class Builder {
        private final Long userId;
        private final List<EmailDto> emails = new ArrayList<>();
        
        public Builder(Long userId) {
            this.userId = userId;
        }
        
        public Builder addEmail(Long emailId, String emailMail) {
            if (emailId != null) {
                emails.add(new EmailDto(emailId, emailMail));
            }
            return this;
        }
        
        public UserWithEmailsDto build() {
            return new UserWithEmailsDto(userId, emails);
        }
    }
}