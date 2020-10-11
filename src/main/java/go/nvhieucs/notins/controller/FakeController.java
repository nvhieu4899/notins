package go.nvhieucs.notins.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "Login controller")
public class FakeController {
    @ApiOperation("Login.")
    @PostMapping("/login")
    public void fakeLogin(@ApiParam("username") @RequestParam String username, @ApiParam("password") @RequestParam String password) {
        throw new IllegalStateException("This method shouldn't be called. It's implemented by Spring Security filters.");
    }
}
