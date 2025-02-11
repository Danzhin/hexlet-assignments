@import exercise.dto.users.UsersPage
@param UsersPage usersPage

@template.layout.page(
    content = @`
        @for(var user : usersPage.getUsers())
            <div>
                <h1><a href="/users/${user.getId()}">${user.getFirstName()} ${user.getLastName()}</a></h1>
            </div>
        @endfor
    `
)