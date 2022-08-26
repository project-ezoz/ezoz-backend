package ezoz.backend_ezoz.global.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import ezoz.backend_ezoz.global.error.ErrorResponse;
import ezoz.backend_ezoz.global.error.exception.AuthenticationException;
import ezoz.backend_ezoz.global.error.exception.BusinessException;
import ezoz.backend_ezoz.global.error.exception.jwt.NotValidTokenException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
public class ExceptionHandlerFilter extends OncePerRequestFilter {

    private final ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        try {
            filterChain.doFilter(request, response);
        } catch (NotValidTokenException | AuthenticationException e) {
            setErrorResponse(response, e);
        }

    }

    private void setErrorResponse(HttpServletResponse response, BusinessException e) throws IOException {
        response.setCharacterEncoding("utf-8");
        response.setStatus(e.getStatus());
        response.setContentType("application/json");
        response.getWriter().write(objectMapper.writeValueAsString(
                ErrorResponse.of(HttpStatus.valueOf(e.getStatus()), List.of(e.getMessage()))));
    }
}
