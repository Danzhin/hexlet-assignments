@param exercise.dto.posts.PostsPage page 

@template.layout.page(
    content = @`
        @for(var post : page.getPosts())
            <div>
                <a href="/posts/${post.getId()}">${post.getName()}</a>
            </div>
        @endfor
        <a href="/posts?page=${page.getNumber()+1}">Предыдущая</a>
        <a href="/posts?page=${page.getNumber()-1}">Следующая</a>
    `
)