import "./styles.css";
import { useHistory } from "react-router-dom";

import imgLogin from "../../assets/login.png";
import { useState } from "react";

export default function Home() {
  const history = useHistory();
  const token = null;

  //form to send file and description to s3 bucket
  // const [form, setForm] = useState({
  //   description: "",
  //   file: null,
  // });

  // //function to send file to s3 bucket
  // const handleFile = (e) => {
  //   setForm({ ...form, file: e.target.files[0] });
  // };

  // //function to send description to s3 bucket
  // const handleDescription = (e) => {
  //   setForm({ ...form, description: e.target.value });
  // };

  // //function to send form to s3 bucket
  // const handleSubmit = (e) => {
  //   e.preventDefault();
  //   const formData = new FormData();
  //   formData.append("file", form.file);
  //   formData.append("description", form.description);
  //   fetch("http://localhost:8090/upload", {
  //     method: "POST",
  //     headers: {
  //       "Content-Type": "multipart/form-data",
  //     },
  //     body: formData,
  //   })
  //     .then((response) => response.json())
  //     .then((data) => {
  //       console.log(data);
  //       history.push("/");
  //     });
  // };
  // return (
  //   <div className="container">
  //     <div className="row">
  //       <div className="col-md-6 offset-md-3">
  //         <div className="card">
  //           <div className="card-body">
  //             <h5 className="card-title">Upload a file</h5>
  //             <form onSubmit={handleSubmit}>
  //               <div className="form-group">
  //                 <label htmlFor="exampleFormControlFile1">
  //                   Select a file to upload
  //                 </label>
  //                 <input
  //                   type="file"
  //                   className="form-control-file"
  //                   id="exampleFormControlFile1"
  //                   onChange={handleFile}
  //                 />
  //               </div>
  //               <div className="form-group">
  //                 <label htmlFor="exampleFormControlTextarea1">
  //                   Write a description
  //                 </label>
  //                 <textarea
  //                   className="form-control"
  //                   id="exampleFormControlTextarea1"
  //                   rows="3"
  //                   onChange={handleDescription}
  //                 />
  //               </div>
  //               <button type="submit" className="btn btn-primary">
  //                 Upload
  //               </button>
  //             </form>
  //           </div>
  //         </div>
  //       </div>
  //     </div>
  //   </div>
  // );

  // fuction to send a file to s3
  async function postFile(route, file, withToken) {
    const config = withToken ? { Authorization: `${token}` } : {};
    try {
      const response = await fetch(`http://localhost:8090/upload`, {
        method: "POST",
        headers: {
          "Content-Type": "multipart/form-data",
          ...config,
        },
        body: file,
      });
      const dataObj = await response.json();
      if (!response.ok) {
        throw new Error(dataObj.message);
      }
      return dataObj;
    } catch (error) {
      console.log(error);
    }
  }

  return (
    <div align="center">
      <div>
        <h2>Spring Boot File Upload to S3</h2>
      </div>
      <div>
        <form
          action="http://localhost:8090/upload"
          method="post"
          enctype="multipart/form-data"
        >
          <p>
            Description:
            <input type="text" name="description" size="30" required />
          </p>

          <p>
            <input type="file" name="file" required />
          </p>

          <p>
            <button type="submit">Submit</button>
          </p>
        </form>
      </div>
    </div>
  );
}
