<!DOCTYPE html>
<html>
<head>
    <title><g:layoutTitle default="LinkSharing Application"/></title>
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'main.css')}"/>
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'style.css')}"/>
    <link rel="stylesheet" href="${resource(dir: 'css/ui-lightness', file: 'jquery-ui-1.8.14.custom.css')}"/>
    <link rel="shortcut icon" href="${resource(dir: 'images', file: 'favicon.ico')}" type="image/x-icon"/>

      <g:javascript library="jquery" />
    <g:layoutHead/>
    <g:javascript library="application"/>
</head>

<body>

<div id="spinner" class="spinner" style="display:none;">
    <img src="${resource(dir: 'images', file: 'spinner.gif')}"
         alt="${message(code: 'spinner.alt', default: 'Loading...')}"/>
</div>

  <b>
    <br>
      <font size="5">
LINKSHARING APPLICATION </b>   </font>  <br>
%{--<div id="grailsLogo"><a href="http://grails.org"><img src="${resource(dir: 'images', file: 'grails_logo.png')}"--}%
                                                      %{--alt="Grails" border="0"/></a></div>--}%




<g:layoutBody/>
</body>
</html>