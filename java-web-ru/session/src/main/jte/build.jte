@import exercise.util.NamedRoutes
@param exercise.dto.LoginPage page

@template.layout.page(
    content = @`
        <form action="${NamedRoutes.loginPath()}" method="post">
            <input type="text" placeholder="Name" name="name" value="${page.getName()}" />
            <input type="password" placeholder="Password" name="password" />
            <input type="submit" value="Login" />
            @if (page.getError() != null)
                <p>${page.getError()}</p>
            @endif
        </form> 
    `
)
