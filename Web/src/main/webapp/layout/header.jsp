<%@page import="com.vgamebase.model.User"%>
<%@page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@page import="org.springframework.security.core.userdetails.UserDetails" %>
<%@page import="org.springframework.security.core.authority.SimpleGrantedAuthority"%>
	<%
	
		UserDetails principalUser = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			principalUser = ((UserDetails) principal);
		} 

	%>
<nav
	class="navbar navbar-expand-lg py-3 navbar-dark bg-danger shadow-sm border-bottom border-light rounded-lg">
	<div class="col-md-3 d-flex justify-content-center">
		<a href="./games" class="navbar-brand float-left"> <img
			src="img/VGameBase2.png" width="45" alt=""
			class="d-inline-block align-middle mr-2"> <span
			class="text-uppercase font-weight-bold"
			style="font-family: 'Lucida Console', Monaco, monospace; font-size: 1.6rem;">VGameBase</span>
		</a>
	</div>
	<div class="col-md-6 d-flex justify-content-center">
		<div id="navbarSupportedContent" class="align-self-center">
			<ul class="navbar-nav">
				<li class="nav-item active border border-Light px-5"><a
					href="./games" class="nav-link"><img src="img/joystick.png">
						Games </a></li>
				<li class="nav-item active border border-Light px-5"><a
					href="./publishers" class="nav-link"><img
						src="img/briefcase.png"> Publishers </a></li>
				<li class="nav-item active border border-Light px-5"><a
					href="./platforms" class="nav-link"><img src="img/device.png">
						Platforms </a></li>
				<% if(principalUser != null && principalUser.getAuthorities().contains(new SimpleGrantedAuthority("Admin"))){ %>		
				<li class="nav-item active border border-Light px-5"><a
					href="./admin" class="nav-link"><img src="img/admin.png">
						Admin</a></li>
				<% } else { %>
				<li class="nav-item active border border-Light px-5"><a
					href="./user" class="nav-link"><img src="img/user.png">
						User</a></li>		
				<% } %>		
			</ul>
		</div>
	</div>
	<% 
		String username = "";
		User userheader = (User) session.getAttribute("user");
		if(userheader == null) {
			username = "visitor";
		} else {
			username = userheader.getUserName();
		}
	%>
	<div class="col-md-3 d-flex justify-content-center">
		<ul class="navbar-nav d-flex justify-content-end">
			<li><p class="mr-3 mt-3 text-white">
					Welcome, <b><%=username%></b> . There are <b><span id="onlineusers">...</span></b>
					users online.
				</p></li>
			<li><a href="./logout" id="logout"
				class="btn btn-light ml-3 border border-dark mt-3" role="button"
				aria-disabled="true">Log out</a></li>
		</ul>

	</div>
</nav>