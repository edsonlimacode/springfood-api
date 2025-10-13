package com.springfood.core.security;

import com.springfood.domain.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

@Component
public class JwtSecretUtils {

    @Autowired
    private RestaurantRepository restaurantRepository;

    public Authentication authentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public Long getUserId() {
        Jwt jwt = (Jwt) this.authentication().getPrincipal();
        Object claim = jwt.getClaim("user_id");

        if (claim == null) return null;

        try {
            return Long.valueOf(claim.toString());
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public boolean managerRestaurants(Long restaurantId) {
        return this.restaurantRepository.getRestaurantByUserId(restaurantId, getUserId());
    }

    public boolean isUserAuthenticated(Long userId) {
        return getUserId() != null && userId != null && getUserId().equals(userId);
    }
}
