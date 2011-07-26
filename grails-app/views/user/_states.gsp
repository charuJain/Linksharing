<%--
  Created by IntelliJ IDEA.
  User: charu
  Date: 21/7/11
  Time: 12:26 PM
  To change this template use File | Settings | File Templates.
--%>

<select>

 <g:each in="${statelist}">
   <option value=${it}> ${it}  </option>

 </g:each>


</select>