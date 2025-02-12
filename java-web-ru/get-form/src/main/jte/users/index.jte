@import exercise.dto.users.UsersPage
@param UsersPage page

@template.layout.page(
    content = @`
        <h1>Список пользователей</h1>
        <form action="/users" method="get">
            <input type="text" name="term" placeholder="Введите имя" value="${page.getTerm()}">
            <button type="submit">Найти</button>
        </form>
    `
)