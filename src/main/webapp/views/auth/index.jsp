<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row d-flex flex-wrap justify-content-center align-items-center h-100">
    <div class="col-md-5 col-12 d-flex justify-content-md-end justify-content-center pe-md-5 px-auto
align-self-md-center align-self-stretch align-items-end">
        <header class="my-5 mr-1">
            <img src="${pageContext.request.contextPath}/images/Background.jpg" class="rounded-3"
                 alt="Store logo"/>
        </header>
    </div>
    <div class="col-md-7 col-12 d-flex justify-content-md-start justify-content-center ps-md-5 px-auto
align-self-md-center align-self-stretch">
        <section class="shadow bg-light px-5 py-1 rounded-4 d-flex align-items-center">
            <form method="post" class="flex-fill">
                <p class="fw-bold fs-1 text-center mt-3">SIGN IN</p>
                <!--Username-->
                <div class="mb-3">
                    <label for="loginUsername" class="form-label">Username</label>
                    <input type="text" class="form-control" id="loginUsername" placeholder="Enter username"
                           name="username"
                           required/>
                </div>
                <!-- Password input -->
                <div class="mb-3">
                    <label for="loginPassword" class="form-label">Password</label>
                    <input type="password" class="form-control" id="loginPassword" placeholder="Enter password"
                           name="password" required>
                </div>
                <!-- 2 column grid layout -->
                <div class="row mb-3">
                    <div class="d-flex justify-content-center">
                        <!-- Checkbox -->
                        <div class="form-check mb-3 mb-md-0 was-validated">
                            <input class="form-check-input" type="checkbox" id="loginCheck" name="remember"/>
                            <label for="loginCheck" class="form-check-label">Remember me</label>
                        </div>
                    </div>
                </div>
                <!-- Submit button -->
                <div class="row px-2">
                    <button type="submit" class="btn mb-3 shadow" formaction="<c:url
        value="/auth/login.do"/>" style="background-color: #4fc284; color: #ffffff">Sign in
                    </button>
                    <span class="text-danger">${message}</span>
                </div>
                <!-- Forgot password-->
                <div class="d-flex justify-content-center">
                    <button type="submit" id="link-button" class="btn btn-link">Forgot password?</button>
                </div>
            </form>
        </section>
    </div>
</div>