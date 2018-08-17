<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<meta name="apple-mobile-web-app-capable" content="yes">
<link rel="apple-touch-icon" href="${pageContext.request.contextPath}/css/apple-touch-icon.png" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css" />
<link rel="shortcut icon" type="image/png" href="${pageContext.request.contextPath}/css/active.png" />
<link rel="icon" type="image/png" href="${pageContext.request.contextPath}/css/active.png" />
<script type="text/javascript">
	if (("standalone" in window.navigator) && window.navigator.standalone) {
		var noddy, remotes = false;
		document
				.addEventListener(
						'click',
						function(event) {
							noddy = event.target;
							while (noddy.nodeName !== "A"
									&& noddy.nodeName !== "HTML") {
								noddy = noddy.parentNode;
							}

							if ('href' in noddy
									&& noddy.href.indexOf('http') !== -1
									&& (noddy.href
											.indexOf(document.location.host) !== -1 || remotes)) {
								event.preventDefault();
								document.location.href = noddy.href;
							}

						}, false);
	}
</script>