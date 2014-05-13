
<!DOCTYPE html>
<html lang="en">
  <head>
    <title>Feed</title>
  </head>
  <body>
      <div class="row">
        <div class="col-md-12">
          <% if (request.search) { %>
          <form role="form" method="GET" action="/search">
            <div class="input-group">
              <input type="text" class="form-control" name="query" placeholder="Search your feed ..." required value="${params.query ?: ''}">
              <span class="input-group-btn">
                <button class="btn btn-primary" type="submit">Search</button>
              </span>
            </div>
          </form>
          <% } else { %>
          <form role="form" method="POST" action="/feed">
            <div class="input-group">
              <input type="text" class="form-control" name="message" placeholder="Share something valuable ..." required>
              <span class="input-group-btn">
                <button class="btn btn-primary" type="submit">Post</button>
              </span>
            </div>
          </form>
          <% } %>
        </div>
      </div>
      <hr/>
      <div class="row">
        <div class="col-md-12">
          <ul class="media-list">
            <% request.posts.each { post -> %>
              <li class="media">
                <a class="pull-left">
                  <img class="media-object" width="64px" height="64px" src="${post.imageUrl ? post.imageUrl : 'http://placekitten.com/64/64?size='}=s64-c" alt="$post.displayName">
                </a>
                <div class="media-body">
                  <% def comments = post.comments %>
                  <h4 class="media-heading">$post.displayName</h4>
                  <p class="post-message ${comments ? 'with-comments' : 'without-comments'}">$post.message</p>
                  <% if (comments) { %>
                    <ul class="media-list">
                      <% post.comments.each { comment -> %>
                          <li class="media">
                            <a class="pull-left">
                              <img class="media-object" width="64px" height="64px" src="${comment.imageUrl ? comment.imageUrl : 'http://placekitten.com/64/64?size='}=s64-c" alt="$comment.displayName">
                            </a>
                            <div class="media-body">
                              <h4 class="media-heading">$comment.displayName</h4>
                              <p>$comment.text</p>
                            </div>
                          </li>
                      <% } %>
                    </ul>
                  <% } %>
                  <% if (!request.search) { %>
                  <div class="row">
                    <div class="comment-form col-md-12  ${comments ? 'with-comments' : 'without-comments'}">
                      <form role="form" method="POST" action="/comment">
                        <input type="hidden" name="id" value="$post.id">
                        <div class="input-group input-group-sm">
                          <input type="text" class="form-control" name="text" placeholder="Comment only if you have something valuable to say ..." required>
                          <span class="input-group-btn">
                            <button class="btn btn-default" type="submit">Comment</button>
                          </span>
                        </div>
                      </form>
                    </div>
                  </div>
                  <% } %>
                </div>
              </li>
            <% } %>
          </ul>
        </div>
      </div>
  </body>
</html>
