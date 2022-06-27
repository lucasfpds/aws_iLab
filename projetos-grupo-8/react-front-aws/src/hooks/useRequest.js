import useGlobal from "./useGlobal";

export default function useRequest() {
  const { token } = useGlobal();

  async function get(route, withToken) {
    const config = withToken ? { Authorization: `${token}` } : {};
    try {
      const response = await fetch(
        `${process.env.REACT_APP_API_BE_URL}${route}`,
        {
          method: "GET",
          headers: {
            "Content-Type": "application/json",
            ...config,
          },
          body: null,
        }
      );
      const dataObj = await response.json();
      if (!response.ok) {
        throw new Error(dataObj.message);
      }
      return dataObj;
    } catch (error) {
      console.log(error);
    }
  }

  async function post(route, body, withToken) {
    const config = withToken ? { Authorization: `${token}` } : {};
    try {
      const response = await fetch(
        `${process.env.REACT_APP_API_BE_URL}${route}`,
        {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
            ...config,
          },
          body: JSON.stringify(body),
        }
      );
      const dataObj = await response.json();

      if (!response.ok) {
        throw new Error(dataObj.message);
      }
      return dataObj;
    } catch (error) {
      console.log(error);
    }
  }

  async function postPedido(route, body, withToken) {
    const config = withToken ? { Authorization: `${token}` } : {};
    try {
      const response = await fetch(
        `${process.env.REACT_APP_API_BE_URL}${route}`,
        {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
            ...config,
          },
          body: JSON.stringify(body),
        }
      );

      return response;
    } catch (error) {
      console.log(error);
    }
  }

  async function put(route, body, withToken) {
    const config = withToken ? { Authorization: `${token}` } : {};
    try {
      const response = await fetch(
        `${process.env.REACT_APP_API_BE_URL}${route}`,
        {
          method: "PUT",
          headers: {
            "Content-Type": "application/json",
            ...config,
          },
          body: JSON.stringify(body),
        }
      );
      const dataObj = await response.json();
      if (!response.ok) {
        throw new Error(dataObj.message);
      }
      return dataObj;
    } catch (error) {
      console.log(error);
    }
  }

  async function patch(route, body, withToken) {
    const config = withToken ? { Authorization: `${token}` } : {};
    try {
      const response = await fetch(
        `${process.env.REACT_APP_API_BE_URL}${route}`,
        {
          method: "PATCH",
          headers: {
            "Content-Type": "application/json",
            ...config,
          },
          body: JSON.stringify(body),
        }
      );
      return response;
    } catch (error) {
      console.log(error);
    }
  }

  // fuction to send a file to s3
  async function postFile(route, file, withToken) {
    
  }

  return {
    get,
    post,
    put,
    patch,
    postPedido,
  };
}
