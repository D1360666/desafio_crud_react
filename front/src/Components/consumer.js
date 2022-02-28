const HOST_API = window._env.HOST_API || "http://localhost:8080/api/"
export default{
    findAll: async () => {
        return fetch(HOST_API + 'list')
        .catch(error => console.error('Error: ', error))
    },

    save: async (request) => {
        return fetch(HOST_API + '/todolist', {
            method: 'POST',
            body: JSON.stringify(request),
            headers: {
                'Content-Type':'application/json'
            }
        })
        .catch(error => console.error('Error: ', error))
    },
    delete: async (listid) => {
        return fetch(HOST_API + listid + '/todolist',{
            method: 'DELETE',
            headers: {
                'Content-Type':'application/json'
            }
        })
        .catch(error => console.error('Error: ', error));
    }
};