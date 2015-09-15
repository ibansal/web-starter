
<tiles:insertTemplate template="/views/layout/plainNew.jsp">
	<tiles:putAttribute name="title" value="Daily Deals on Snapdeal.com" />
	<tiles:putAttribute name="head">
		<link href="${path.css('mobile/style2.css')}" rel="stylesheet"
			type="text/css" />
		<link href="${path.css('mobile/style2.css')}" media="handheld"
			rel="stylesheet" type="text/css" />
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta name="HandheldFriendly" content="true" />
		<meta name="DC.description" content="">
		<meta name="viewport" content="initial-scale=1.0" />
	</tiles:putAttribute>
	<tiles:putAttribute name="body">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td><table width="100%" border="0" cellspacing="0"
						cellpadding="0" class="topborder">
						<tr>
							<td><div class="logo">
									<a id="home" href="/"></a>
								</div>
							</td>
							<!-- <td align="right"><a href="/selectCity" class="select_city">Delhi</a>
							</td>  -->
						</tr>
						<c:choose>
							<c:when test="${param['code'] == '404'}">
								<tr>
									<td colspan="2"><table width="100%" border="0"
											cellspacing="0" cellpadding="0" class="topnav_strip">
											<tr>
												<td width="100%" align="left">Page Not Found</td>
											</tr>
										</table>
									</td>
								</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td align="left"><table width="100%" border="0" cellspacing="0"
						cellpadding="0">
						<tr>
							<td><table width="100%" border="0" cellspacing="0"
									cellpadding="0" class="vouchers_table">
									<tr>
										<td align="left" class="vouchers_borderbottom"><span>The
												Page you are requesting is not available</span>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			</c:when>
			<c:otherwise>
				<div align="center">
					<c:choose>
						<c:when test="${param['code'] == '500'}">
							<tr>
								<td colspan="2"><table width="100%" border="0"
										cellspacing="0" cellpadding="0" class="topnav_strip">
										<tr>
											<td width="100%" align="left">Internal Server Error</td>
										</tr>
									</table>
								</td>
							</tr>
		</table>
		</td>
		</tr>
		<tr>
			<td align="left"><table width="100%" border="0" cellspacing="0"
					cellpadding="0">
					<tr>
						<td><table width="100%" border="0" cellspacing="0"
								cellpadding="0" class="vouchers_table">
								<tr>
									<td align="left" class="vouchers_borderbottom"><span>Internal
											Server Error. Please try after some time. Thanks</span>
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		</c:when>
		<c:when test="${param['code'] == '403'}">
			<tr>
				<td colspan="2"><table width="100%" border="0" cellspacing="0"
						cellpadding="0" class="topnav_strip">
						<tr>
							<td width="100%" align="left">Error</td>
						</tr>
					</table>
				</td>
			</tr>
			</table>
			</td>
			</tr>
			<tr>
				<td align="left"><table width="100%" border="0" cellspacing="0"
						cellpadding="0">
						<tr>
							<td><table width="100%" border="0" cellspacing="0"
									cellpadding="0" class="vouchers_table">
									<tr>
										<td align="left" class="vouchers_borderbottom"><span>Error
												on page. Please try after some time.</span>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</c:when>
		<c:otherwise>
			<tr>
				<td colspan="2"><table width="100%" border="0" cellspacing="0"
						cellpadding="0" class="topnav_strip">
						<tr>
							<td width="100%" align="left">Error</td>
						</tr>
					</table>
				</td>
			</tr>
			</table>
			</td>
			</tr>
			<tr>
				<td align="left"><table width="100%" border="0" cellspacing="0"
						cellpadding="0">
						<tr>
							<td><table width="100%" border="0" cellspacing="0"
									cellpadding="0" class="vouchers_table">
									<tr>
										<td align="left" class="vouchers_borderbottom"><span>Error
												on page. Please try after some time.</span>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</c:otherwise>
		</c:choose>
		</div>
		</c:otherwise>
		</c:choose>

		<tr>
			<td><table width="100%" border="0" cellspacing="0"
					cellpadding="0" class="footer">
					<c:choose>
						<c:when test="${user != null}">
							<tr>
								<td><a href="/myaccount">My Account</a>| <a href="/help">Help</a>
									| <a href="/terms">T &amp; C</a></td>
							</tr>
						</c:when>
						<c:otherwise>
							<tr>
								<td><a href="/signin">Sign In</a> | <a href="/help">Help</a>
									<a href="/terms">T &amp; C</a></td>
							</tr>
						</c:otherwise>
					</c:choose>

				</table></td>
		</tr>
		</table>
		<tr>
			<td><a href="http://snapdeal.com/?view=desktop"
				style="font-size: 14px; color: #000">Switch to Desktop Site >></a></td>
		</tr>
		<!-- SiteCatalyst code version: H.24.1.

Copyright 1996-2011 Adobe, Inc. All Rights Reserved

More info available at http://www.omniture.com -->


		<!-- End SiteCatalyst code version: H.24.1. -->
	</tiles:putAttribute>
</tiles:insertTemplate>
