@import exercise.util.NamedRoutes
@param exercise.dto.MainPage page

@template.layout.page(
    content = @`
        @if (page.getName() == null)
            <a href="${NamedRoutes.buildSessionPath()}">Войти</a>
        @else
            <p>Привет, ${page.getName()}!</p>
            <form action="${NamedRoutes.logoutPath()}" method="post">
                <input type="submit" value="Выйти" />
            </form>
        @endif
    `
)