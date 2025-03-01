@param exercise.dto.posts.PostPage page 

@template.layout.page(
    content = @`
        <div>
            <h3>${page.getPost().getName()}</h3>
            <p>${page.getPost().getBody()}</p>
        </div>
    `
)