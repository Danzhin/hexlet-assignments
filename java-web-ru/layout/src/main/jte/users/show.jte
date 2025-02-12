@import exercise.dto.users.UserPage
@param UserPage page

@template.layout.page(
    content = @`
        <div>
            Имя ${page.getUser().getFirstName()} Фамилия ${page.getUser().getLastName()} Email ${page.getUser().getEmail()}
        </div>
    `
)
