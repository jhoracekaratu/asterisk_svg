import { useCallback, useContext, useEffect, useState } from "react";
import styles from "./Main.module.css"
import { event, isEmptyObject } from "jquery";
import { Link, Outlet, useNavigate } from "react-router-dom";
import { Button } from "bootstrap";
import Editor from "./components/Editor";
import { UserContext } from "./UserContext";
import Kstyles from "./KIndex.module.css"
import { deleteApi,postApi, getApi } from "./FetchAPI";
function KIndex(){
  const navigate=useNavigate();
  const userContext = useContext(UserContext);
   const [editorLoaded, setEditorLoaded] = useState(false);
   const [fetchedPosts, setFetchedPosts] = useState();
   const [postPart, setPostParts] = useState({
      
      title:""
    });
    const [posts,setPosts]=useState([])
    const handleDelete=(post)=>{
      
      const deleteUrl='http://localhost:8082/api/users/'+userContext.user.id+'/posts/'+post.id;
      const result=deleteApi(deleteUrl,"admin","admin",)
     result.then(res=>{
     setPosts(res.posts)
     })
      
    }
 
    const handleClose = (part) =>{ 
      const postUrl='http://localhost:8082/api/users/'+userContext.user.id+'/posts';
     const res=postApi(postUrl,"admin","admin",part)
     res.then(res=>{
      setPosts(res.posts)
     
     })
        hideModal("edit");
    
    
    }

    const hideModal=()=>{
      let modal=document.getElementById("exampleModal")
      
      modal.classList.add("hide");
      modal.style.display="none";
      
  }
  
    const fetchPostData=useCallback(()=>{
      const url="http://localhost:8082/api/users/"+userContext.user.id+"/posts"
      getApi("admin","admin",url).then((value)=>{
        console.log(value)
        console.log(userContext)
        setPosts(value);
        
      });   
    },[]);
   

    const showModal=()=>{
      let modal=document.getElementById("exampleModal")
        modal.classList.add("show");
        modal.style.display="block";
    }

    
    useEffect(()=>{
      console.log(userContext)
        fetchPostData();
    },[])
        



    return(
    <>
     
    <div className={`Main`} >

    <div className={styles.content}>

     <h1 className={`${styles.header}`}>My Posts   
   

     <button type="button" className="btn btn-secondary" onClick={(event)=>showModal()}>+</button>
     </h1>
     <ol>

     {
        posts.length>0&&  Array.prototype.map.call(posts, post => {
           return <li key={post.id}>

<div className={Kstyles.container}>
          <div className={Kstyles.link}>
           <Link to={"edit"} state={post}>{post.title}</Link>
           </div>
           <div className={Kstyles.delete_button}>
           <button type="button" className="btn btn-secondary" onClick={event=>{
           handleDelete(post)}}
           >Delete</button>
</div>
           </div>
           </li>
           
          
        })
     }
     </ol>

     {/* modal starts here */}
      {/* my modal start */}
      <div className="modal fade" id="exampleModal" tabIndex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div className="modal-dialog  modal-dialog-scrollable modal-dialog-centered">
    <div className="modal-content">
      <div className="modal-header">
        <h1 className="modal-title fs-5" id="exampleModalLabel">Enter Modal Title</h1>
        <button type="button" className="btn-close" data-bs-dismiss="modal" aria-label="Close" onClick={()=>hideModal()}></button>
      </div>
      <div className="modal-body">
      <input type="textarea" name="editor" id="editor"  
      value={postPart.title}
       onChange={(event)=>{
         setPostParts((prev)=>{
      
      return {...prev,title:event.target.value}
    })
       }}/>
    
      
      </div>
      <div className="modal-footer">
        <button type="button" className="btn btn-secondary" data-bs-dismiss="modal" onClick={()=>hideModal()}>Close</button>
        <button type="button" className="btn btn-primary" onClick={()=>{handleClose(postPart)}}>Save changes</button>
      </div>
    </div>
  </div>
</div>
{/* my modal end */}
     {/* modal stops here */}
    </div>
    
    </div>
  
    </>
    
    );
    }
    export default KIndex;
