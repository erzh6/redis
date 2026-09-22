package com.example.redispostgres.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RedisHash("sessions")
public class Session {

    @Id
    private String sessionId;

    private Long userId;
    private LocalDateTime loginTime;
    private LocalDateTime lastActiveTime;
}
