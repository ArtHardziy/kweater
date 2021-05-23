<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>
<@c.page>
    <h2>Login page</h2>
    <div style="color: red">${message!" "}</div>
    <h2>Add new user:</h2>
    <@l.login "/registration"/>
</@c.page>