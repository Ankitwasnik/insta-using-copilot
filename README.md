# Social Media App Using GitHub Copilot
This is a simple app developed to evaluate the capabilities of GitHub Copilot. The app is a social media app that allows users to post images along with the caption. Users can react and comment on the post.

## Tools/Technologies Used
    Java 17
    Spring Boot
    Postgres

This app is still under development.

Flow of the app is as follows:
1. User can register using the email.
2. This app is using basic authentication, so user needs to login using the email and password.
3. User can now post images along with the caption.
4. User can react and comment on the post.
5. Rating of a user is based on the following criteria:
- If a user submits a post, the user will get 10 points.
- If there are reactions to the post, the user who submitted the post will get an additional 1 point for each reaction.
- If there are comments on the post, the user who submitted the post will get an additional 2 point for each comment by other users.
