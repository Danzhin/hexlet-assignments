@import exercise.dto.users.UserPage
@param UserPage userPage

@template.layout.page(
    content = @`
        <div>
            Имя ${userPage.getUser().getFirstName()} Фамилия ${userPage.getUser().getLastName()} Email ${userPage.getUser().getEmail()}
        </div>
    `
)
