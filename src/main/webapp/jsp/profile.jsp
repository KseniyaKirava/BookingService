<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<%@ include file="../include/head.htm" %>
<body>
<div class="container">
    <%@ include file="../include/menu.htm" %>


    <form class="form-horizontal" method="post" command="do?command=Profile">
        <fieldset>
            <!-- Form Name -->


            <div class="row justify-content-left align-items-start">
                <div class="col-md-2">
                    <ul class="nav navbar-nav nav-pills nav-stacked">
                        <li class="active nav-item">
                            <a class="nav-link" style="font-size: 14pt; color: #949494;" href="do?command=Profile">Edit my profile</a>
                        </li>

                        <li class="nav-item">
                            <a class="nav-link" style="font-size: 14pt; color: #949494;" href="do?command=Requests">My requests</a>
                        </li>

                        <li class="nav-item">
                            <a class="nav-link" style="font-size: 14pt; color: #949494;" href="do?command=Reservations">My reservations</a>
                        </li>

                    </ul>

                </div>


                <div class="col-md-10">

                    <div class="row">
                        <div class="col-md-6 text-left">
                            <div class="form-group">
                                <div class="col-md-auto">
                                    <legend>My profile - Edit my profile</legend>
                                </div>
                            </div>
                        </div>


                        <div class="col-md-6 text-right">
                            <div class="form-group">
                                <div class="col-md-auto">
                                    <button id="logout" name="logout" class="btn btn-white">LOG OUT</button>
                                    <button id="deletemyaccount" name="deletemyaccount" class="btn btn-primary">DELETE
                                        MY ACCOUNT
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>


                    <!-- Text input-->
                    <div class="row justify-content-left align-items-start">

                        <div class="col-md-6">
                            <div class="form-group">
                                <label class="col-md-auto control-label" for="username">Login</label>
                                <div class="col-md-auto">
                                    <input id="username" value="${username}" name="username"
                                           type="text" class="form-control input-md"
                                           required="" readonly>

                                </div>
                            </div>
                        </div>

                        <div class="col-md-6">
                            <div class="form-group">
                                <label class="col-md-auto control-label" for="email">Email</label>
                                <div class="col-md-auto">
                                    <input id="email" value="${email}" name="email" type="text" placeholder="e-mail"
                                           class="form-control input-md" required="">

                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="row justify-content-left align-items-start">
                        <!-- Password input-->
                        <div class="col-md-6">
                            <div class="form-group">
                                <label class="col-md-auto control-label" for="password">Password</label>
                                <div class="col-md-auto">
                                    <input id="password" value="${password}" name="password" type="password"
                                           placeholder="password" class="form-control input-md" required="">
                                    <span class="help-block">*min 6 symbols</span>
                                </div>
                            </div>
                        </div>


                        <!-- Text input-->

                        <div class="col-md-6">
                            <div class="form-group">
                                <label class="col-md-auto control-label" for="first_name">First name</label>
                                <div class="col-md-auto">
                                    <input id="first_name" value="${first_name}" name="first_name" type="text"
                                           placeholder="first name" class="form-control input-md" required="">

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
                                    <input id="last_name" value="${last_name}" name="last_name" type="text"
                                           placeholder="last name" class="form-control input-md" required="">

                                </div>
                            </div>
                        </div>


                        <!-- Text input-->

                        <div class="col-md-6">
                            <div class="form-group">
                                <label class="col-md-auto control-label" for="middle_name">Middle name</label>
                                <div class="col-md-auto">
                                    <input id="middle_name" value="${middle_name}" name="middle_name" type="text"
                                           placeholder="middle name" class="form-control input-md">

                                </div>
                            </div>
                        </div>
                    </div>


                    <!-- Button (Double) -->
                    <div class="form-group">
                        <div class="col-md-auto text-right">
                            <button id="saveinfo" name="saveinfo" class="btn btn-primary">SAVE INFO</button>
                        </div>
                    </div>
                </div>

            </div>
        </fieldset>
    </form>
</div>
</body>
</html>


