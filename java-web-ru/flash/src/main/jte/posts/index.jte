@import exercise.dto.posts.PostsPage
@import exercise.util.NamedRoutes
@import exercise.model.Post
@param PostsPage page

@template.layout.page(
    page = page,
    content = @`
        <h1>All Posts</h1>
        <ul>
            @for (var post : page.getPosts()) 
                <li>
                    <a href="${NamedRoutes.postPath(post.getId())}">${post.getName()}</a>
                </li>
            @endfor
        </ul>
        <a href="${NamedRoutes.buildPostPath()}">Create a new post</a>
    `
)