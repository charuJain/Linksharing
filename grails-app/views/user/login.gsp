<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head><meta name="layout" content="main"/></head>


<body>

<g:if test="${flash.message}">
    <div class="message">${flash.message}</div>
</g:if>


<g:form controller="user" action="loginHandler" name="formId">
    username <input type="text" name="username" id="userid">    <br>
    password<input type="password" name="password" id="passid">  <br>

    <div id='myResult'>

    </div>


    <input type="button" name='getdate' value='getdate' id='mybutton'/>
    <input type="button" name="checkUser" value="Am valid" id="checkId"/>



    Submit <input type="submit" value="Log In Now!">
    <br><br>
    <g:link controller="user" action="registerHandler">sign up now</g:link>

</g:form>
<script type="text/javascript">


    function updateDateTime() {

        var x = $('#formId').serialize()
//            var name=$("#userid").val()
//            var passwd=$("#passid").val()
        console.debug(x)
        jQuery.get(
                "${createLink(controller:'user' ,action:'dateof')}", x, function(data) {
                    //console.debug(data)
                    jQuery("#myResult").text(data.message)
                }
        )
    }
    $(function() {
        $('#checkId').click(updateDateTime)
    })

</script>

</body>
</html>