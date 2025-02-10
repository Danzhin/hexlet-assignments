@import exercise.dto.users.UsersPage
@param UsersPage usersPage

<main>
    @for(var user : usersPage.getUsers())
        <div>
            <h1><a href="/courses/${user.getId()}">${user.getFirstName()} ${user.getLastName()}</a></h1>
        </div>
    @endfor
</main>