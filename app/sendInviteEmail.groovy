mail.send from: 'youradminuser@example.com',
    to: params.email,
    subject: 'Invitation to The Hand of Friends'
    textBody: """Hello,
    the user ${params.nickname} really cherrish you as a friend and want you to join
    him at http://handoffriends.com/join/$params.userId

    Team HoF

    """.stripIndent()
