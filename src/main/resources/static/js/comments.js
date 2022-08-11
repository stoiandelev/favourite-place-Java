const placeId = document.getElementById("placeId").value
const commentCtnr = document.getElementById("commentCtnr")

const allComments = []

const displayComments = (comment) => {
    commentCtnr.innerHTML = comment.map(
        (c) => {
            return asComment(c)
        }
    ).join('')
}


//JSON to DOM
function asComment(c) {
    let commentHTML = `<div id="commentCtnr-${c.commentId}">`
    commentHTML += `<h4>${c.author} (${c.created})</h4><br/>`
    commentHTML += `<p>${c.textContent}</p>`
    commentHTML += `</div>`

    return commentHTML
}

fetch(`http://localhost:8080/api/${placeId}/comments`)
    .then(response => response.json())
    .then(data => {
        for (let comment of data) {
            allComments.push(comment)
        }
        displayComments(allComments)
    })


const csrfHeaderName = document.head.querySelector('[name ="_csrf_header"]').content
const csrfHeaderValue = document.head.querySelector('[name ="_csrf"]').content


const commentForm = document.getElementById("commentForm");
commentForm.addEventListener("submit", handleCommentSubmit);

async function handleCommentSubmit(event) {
    event.preventDefault();

    const form = event.currentTarget;
    const url = form.action
    const formData = new FormData(form)

    try {
        const responseData =await postFormDataAsJson({url, formData})
        commentCtnr.insertAdjacentHTML("afterbegin", asComment(responseData))
        form.reset()
    } catch (error) {
        let errorObj = JSON.parse(error.message)
        if (errorObj.fieldWithErrors) {
            errorObj.fieldWithErrors.forEach(
                e => {
                    let elementWithError = document.getElementById(e)
                    if (elementWithError) {
                        elementWithError.classList.add("is-invalid")
                    }
                }

            )
        }
    }


}

async function postFormDataAsJson({url, formData}) {

    const plainFormData = Object.fromEntries(formData.entries())
    const formDataAsJsonString = JSON.stringify(plainFormData)

    const fetchOption = {
        method: "POST",
        headers: {
            [csrfHeaderName] : csrfHeaderValue,
            "Content-Type" :  "application/json",
            "Accept" : "application/json"
        },
        body: formDataAsJsonString

    }
    const response = await fetch(url, fetchOption)

    if (!response.ok) {
        const errorMassage = await response.text()
        throw new Error(errorMassage)
    }

    return response.json()

}

