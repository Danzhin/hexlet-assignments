@import exercise.dto.users.UserPage
@param UserPage userPage

@template.layout.page(
    content = @`
        <div>
            <p>Имя ${userPage.getUser().getFirstName()}</p>
            <p>Фамилия ${userPage.getUser().getLastName()}</p>
            <p>Email ${userPage.getUser().getEmail()}</p>
        </div>
    `
)
