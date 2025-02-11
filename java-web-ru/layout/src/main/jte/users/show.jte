@import exercise.dto.users.UserPage
@param UserPage page

@template.layout.page(
    content = @`
        <div>
            <p>Имя ${page.getUser().getFirstName()}<p>
            <p>Фамилия ${page.getUser().getLastName()}<p>
            <p>Email $${page.getUser().getEmail()}<p>
        </div>
    `
)