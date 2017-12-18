<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh">
	<head>
		<meta charset="utf-8" />
		<title>登录页面</title>
		<meta name="keywords" content="权限控制系统" />
		<meta name="description" content="自定义权限控制系统" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<link rel="stylesheet" href="/resources/css/bootstrap.min.css" />
		<link rel="stylesheet" href="/resources/css/font-awesome.min.css" />
		<link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Open+Sans:400,300" />
		<link rel="stylesheet" href="/resources/css/ace.min.css" />
		<link rel="stylesheet" href="/resources/css/ace-rtl.min.css" />
	</head>

	<body class="login-layout">
		<div class="main-container">
			<div class="main-content">
				<div class="row">
					<div class="col-sm-10 col-sm-offset-1">
						<div class="login-container">
							<div class="center">
								<h1>
									<i class="icon-leaf green"></i>
									<span class="white">权限控制系统</span>
								</h1>
								<h4 class="blue">&copy; moonlightL</h4>
							</div>

							<div class="space-6"></div>

							<div class="position-relative">
								<div id="login-box" class="login-box visible widget-box no-border">
									<div class="widget-body">
										<div class="widget-main">
											<h4 class="header blue lighter bigger">
												<i class="icon-coffee green"></i>
												请输入您的账户信息
											</h4>

											<div class="space-6"></div>

											<form>
												<fieldset>
													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="text" class="form-control" placeholder="用户名" id="user-name"/>
															<i class="icon-user"></i>
														</span>
													</label>

													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="password" class="form-control" placeholder="密码" id="password"/>
															<i class="icon-lock"></i>
														</span>
													</label>

													<div class="space"></div>

													<div class="clearfix">
														<label class="inline">
															<input type="checkbox" class="ace" />
															<span class="lbl"> 记住我</span>
														</label>

														<button type="button" class="width-35 pull-right btn btn-sm btn-primary" id="submit-btn">
															<i class="icon-key"></i>
															登陆
														</button>
													</div>

													<div class="space-4"></div>
												</fieldset>
											</form>

											<div class="social-or-login center">
												<span class="bigger-110">第三方登陆</span>
											</div>

											<div class="social-login center">
												<a class="btn btn-primary">
													<i class="icon-facebook"></i>
												</a>

												<a class="btn btn-info">
													<i class="icon-twitter"></i>
												</a>

												<a class="btn btn-danger">
													<i class="icon-google-plus"></i>
												</a>
											</div>
										</div><!-- /widget-main -->

										<div class="toolbar clearfix">
											<div>
												<a href="#" onclick="show_box('forgot-box'); return false;" class="forgot-password-link">
													<i class="icon-arrow-left"></i>
													忘记密码
												</a>
											</div>

											<div>
												<a href="#" onclick="show_box('signup-box'); return false;" class="user-signup-link">
													我要注册
													<i class="icon-arrow-right"></i>
												</a>
											</div>
										</div>
									</div><!-- /widget-body -->
								</div><!-- /login-box -->
							</div>
						</div>
					</div><!-- /.col -->
				</div><!-- /.row -->
			</div>
		</div><!-- /.main-container -->
		<script>
			// 防止 session 过期，iframe 框显示登录界面
			if (window.top != window) {
				window.top.location.href = window.location.href;
			}
		</script>
		<script type="text/javascript" src="/resources/js/jquery-1.11.3.js"></script>
		<script type="text/javascript" src="/resources/js/login.js"></script>
</body>
</html>
