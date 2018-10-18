<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<%@ include file="../include/head.htm" %>
<body>
<div class="container">
    <%@ include file="../include/menu.htm" %>

    <form class="form-horizontal" method="post" command="do?command=Signup">


        <fieldset>

            <!-- Form Name -->
            <legend>Sign Up</legend>

            <div class="row justify-content-left align-items-start">

                <div class="col-md-6">
                    <div class="form-group">
                        <label class="col-md-auto control-label" for="username">Login</label>
                        <div class="col-md-auto">
                            <input id="username" value="" name="username" type="text"
                                   placeholder="login" class="form-control input-md" required="">
                        </div>
                    </div>
                </div>


                <div class="col-md-6">
                    <div class="form-group">
                        <label class="col-md-auto control-label" for="email">Email</label>
                        <div class="col-md-auto">
                            <input id="email" value="" name="email" type="text" placeholder="e-mail"
                                   class="form-control input-md" required="">

                        </div>
                    </div>
                </div>
            </div>

            <!-- Password input-->
            <div class="row justify-content-left align-items-start">
                <div class="col-md-6">
                    <div class="form-group">
                        <label class="col-md-auto control-label" for="password">Password</label>
                        <div class="col-md-auto">
                            <input id="password" value="" name="password" type="password"
                                   placeholder="password" class="form-control input-md" required="">
                            <span class="help-block">* a-zA-Z0-9 (min 6 symbols)</span>
                        </div>
                    </div>
                </div>

                <div class="col-md-6">
                    <div class="form-group">
                        <label class="col-md-auto control-label" for="first_name">First name</label>
                        <div class="col-md-auto">
                            <input id="first_name" value="" name="first_name" type="text" placeholder="first name"
                                   class="form-control input-md" required="">

                        </div>
                    </div>
                </div>
            </div>

            <div class="row justify-content-left align-items-start">
                <!-- Text input-->
                <div class="col-md-6">
                    <div class="form-group">
                        <label class="col-md-auto control-label" for="last_name">Last name</label>
                        <div class="col-md-auto">
                            <input id="last_name" value="" name="last_name" type="text" placeholder="last name"
                                   class="form-control input-md" required="">

                        </div>
                    </div>
                </div>

                <div class="col-md-6">
                    <div class="form-group">
                        <label class="col-md-auto control-label" for="middle_name">Middle name</label>
                        <div class="col-md-auto">
                            <input id="middle_name" value="" name="middle_name" type="text"
                                   placeholder="middle name" class="form-control input-md">

                        </div>
                    </div>
                </div>

            </div>


            <!-- Button -->
            <div class="form-group">
                <label class="col-md-4 control-label" for="singupbutton"></label>
                <div class="col-md-4">
                    <button id="singupbutton" name="singupbutton" class="btn btn-primary">SIGN UP</button>
                </div>
            </div>

        </fieldset>
    </form>


</div>
</body>
</html>




