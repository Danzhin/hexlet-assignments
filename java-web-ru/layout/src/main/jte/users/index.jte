@import exercise.dto.users.UsersPage
@param UsersPage page

@template.layout.page(
    content = @`
        <h1>Пользователи</h1>
        @for(var user : page.getUsers())
            <div>
                <a href="/users/${user.getId()}">${user.getFirstName()} ${user.getLastName()}</a>
            </div>
        @endfor
    `
)