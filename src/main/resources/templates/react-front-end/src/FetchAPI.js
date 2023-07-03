export const deleteApi = (url, username, password) => {
  const deldata=(async () => {
    const rawResponse = await fetch(url, {
      method: "DELETE",
      headers: {
        Accept: "application/json",
        "Content-Type": "application/json",
        Authorization: "Basic " + btoa("admin:admin"),
      },
    });
    const content = await rawResponse.json();
    
    return content;
  })();
  return deldata;
};

export const getApi = (
  username,
  password,
  url
) => {
 
  const allData = fetch(url, {
    method: "GET",
    headers: {
      Authorization: "Basic " + btoa("admin:admin"),
    },
  })
    .then((response) => {
      return response.json();
    })
    .then((posts) => {
      return posts;
    })
    .catch((error) => console.log(error));
  return allData;
   
};

export const postApi = (url, username, password, data) => {
  let result=(async () => {
    const rawResponse = await fetch(url, {
      method: "POST",
      headers: {
        Accept: "application/json",
        "Content-Type": "application/json",
        Authorization: "Basic " + btoa("admin:admin"),
      },
      body: JSON.stringify(data),
    });
    result = await rawResponse.json();
return result;
  })();
  return result;
};

export const updateApi = (url, username, password, data) => {
  const content=(async () => {
    const rawResponse = await fetch(url, {
      method: "PUT",
      headers: {
        Accept: "application/json",
        "Content-Type": "application/json",
        Authorization: "Basic " + btoa("admin:admin"),
      },
      body: JSON.stringify(data),
    });
    const content = await rawResponse.json();
    return content;

  })();
  return content;
};
