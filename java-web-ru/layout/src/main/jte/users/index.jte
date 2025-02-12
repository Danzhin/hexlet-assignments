@import exercise.dto.users.UsersPage
@param UsersPage usersPage

@template.layout.page(
    content = @`
        @for(var user : usersPage.getUsers())
            <div>
                <a href="/users/${user.getId()}">${user.getFirstName()} ${user.getLastName()}</a>
            </div>
        @endfor
    `
)