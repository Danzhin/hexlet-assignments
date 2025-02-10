@import exercise.dto.users.UserPage
@param UserPage userPage

<main>
    <h1>${userPage.getUser().getFirstName()} ${userPage.getUser().getLastName()}</h1>
    <p>${userPage.getUser().getEmail()}</p>
</main>