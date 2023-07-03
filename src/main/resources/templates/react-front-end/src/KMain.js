import { useCallback,useContext, useEffect, useState } from "react";
import styles from "./Main.module.css"
import { Button, Modal } from 'react-bootstrap';
import Editor from "./components/Editor";
import { useLocation } from "react-router-dom";
import { getApi,deleteApi, postApi, updateApi } from "./FetchAPI";
import { UserContext } from "./UserContext";
function Main(props){

// const user=useContext(UserContext)
  const [editorLoaded, setEditorLoaded] = useState(false);
  const userContext = useContext(UserContext);
  const [selectedGroup, setSelectedGroup] = useState();
  const [show, setShow] = useState(false);
  const [groups, setGroups] = useState();
  const [postPart, setPostParts] = useState({
    id:null,
    sub_content:"",
    sub_title:""
  });
  const location=useLocation();
let propsData=location.state;

  const [post,setPost]=useState([])
  useEffect(()=>{
    setEditorLoaded(true);
     setPost(propsData)
     fetchGroups();
     fetchSelectedGroups();
  },[]);
 

  const fetchPostData=useCallback(()=>{
    const url="http://localhost:8082/api/users/"+userContext.user.id+"/posts"
    getApi("admin","admin",url).then((value)=>{
      // setPost(value.)
    });   
  },[]);

  const fetchGroups=useCallback(()=>{
    const url="http://localhost:8082/api/groups"
    getApi("admin","admin",url).then((value)=>{
      setGroups(value)
    });   
  },[]);

  const fetchSelectedGroups=useCallback(()=>{
    const url="http://localhost:8082/api/users/"+userContext.user.id+"/posts/"+propsData.id+"/groups"
    getApi("admin","admin",url).then((value)=>{
     
     setSelectedGroup(value)
    });   
  },[]);
  

    const handleClose = (part,operationType) =>{ 
        if(operationType=="edit"){
         const updateUrl='http://localhost:8082/api/posts';
         const result=updateApi(updateUrl,"admin","admin",part);
         result.then(res=>{
          setPost(res)
         })


        //  return posts and assign to propsData then refresh
          hideModal("edit");
        // setShow(false);
      }else if(operationType=="delete") {//start here
        const deleteUrl='http://localhost:8082/api/posts/'+postPart.id;
const deletePosts=deleteApi(deleteUrl,"admin","admin");
// let fetchedPosts=fetchPostData();
deletePosts.then(del=>{
  setPost(del)
})
        hideModal("delete");
      }//end here
      else{
        //save post
        const postUrl='http://localhost:8082/api/users/'+userContext.user.id+'/posts/'+propsData.id+"/postparts";
        const result=  postApi(postUrl,"admin","admin",postPart)
        result.then(res=>{
          setPost(res)
        })
        hideModal("create");
      }
      }

      const handleGroupChange=(data)=>{
        //save post
        let groupObject={groupname:selectedGroup}
        const postUrl='http://localhost:8082/api/posts/'+post.id+'/groups';
        const result=  postApi(postUrl,"admin","admin",groupObject)
        result.then(res=>{
          setPost(res)
        })
        hideModal("create");
      }

        const handleGroupsSave=()=>{
          console.log(selectedGroup)
           const postUrl='http://localhost:8082/api/posts/'+propsData.id+"/groups/v2";
        const result=  postApi(postUrl,"admin","admin",{"list":selectedGroup})
        result.then(res=>{
          console.log(res)
          setSelectedGroup(res.groups)
        })
      }

const handlePostPartChange = (event) => {

    setPostParts((prev)=>{
      
      return {...prev,sub_content:event.target.value}
    })
    
   
   
};


    const showModal=(part,operationType)=>{
      setPostParts(part)
      let modal=""
      if(operationType=="edit"){
       modal =document.getElementById("exampleModal")
      }
      else if(operationType=="delete"){
       modal=document.getElementById("deletemodal")
      }else{
        setPostParts((prev)=>{
      
          return {...prev,sub_content:"",id:null,sub_title:""}
        })
        modal=document.getElementById("createmodal")
      }
        
        modal.classList.add("show");
        modal.style.display="block";
        
    }
    const hideModal=(operationType)=>{
      let modal=""
      if(operationType=="edit"){
        modal=document.getElementById("exampleModal")
      }
      else if(operationType=="delete"){
        modal=document.getElementById("deletemodal")
      }
      else{
        modal=document.getElementById("createmodal")
      }
      modal.classList.add("hide");
      modal.style.display="none";
      
  }
   
        



    return(
    <>
    <div className={`Main`} >

    <div className={styles.content}>

<div className=" d-flex flex-row justify-content-between  align-content-center align-items-center">
<div className="w-50">
<h1 className={`${styles.header}`} >{post.title}
 
 
 </h1>

</div>

<div className="w-25">
<button type="button" className="btn btn-secondary" onClick={(event)=>showModal()}>+</button>
</div>

<div className="w-25 ">
<button type="button" className="btn btn-secondary" onClick={()=>{
const modal=document.getElementById("groupsmodal")
 modal.classList.add("show");
 modal.classList.remove("hide");
 modal.style.display="block";
 }
}>Manage Groups</button>  

</div>
</div>
  
{
   
    post.title!=undefined &&  Array.prototype.map.call(post.postParts, part => {
      
    
            return (
            <div className={`${styles.pills}`} key={part.id}  >
            <Button className="btn btn-primary" onClick={(event)=>showModal(part,"edit")}>Edit</Button>
            <Button className="btn btn-primary  mx-5" onClick={(event)=>showModal(part,"delete")}>Delete</Button>
            <h2 className={`${styles.header}`}>{part.sub_title}</h2>
            <p className={`${styles.body}`} dangerouslySetInnerHTML={{ __html: part.sub_content }}></p>
     </div>
                
            );
    
    }
   
)
    
}







      {/* my modal start */}
      <div className="modal fade" id="exampleModal" tabIndex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div className="modal-dialog  modal-dialog-scrollable modal-dialog-centered">
    <div className="modal-content">
      <div className="modal-header">
        <h1 className="modal-title fs-5" id="exampleModalLabel">{postPart!=undefined && postPart.sub_title}</h1>
        <button type="button" className="btn-close" data-bs-dismiss="modal" aria-label="Close" onClick={()=>hideModal("edit")}></button>
      </div>
      <div className="modal-body">
      {/* <input type="textarea" name="editor" id="editor"  value={postPart.sub_content} onChange={(event)=>handlePostPartChange(event)}/> */}
    
      <Editor
        name="description"
        value={postPart.sub_content}
        onChange={(data) => {
          setPostParts((prev)=>{
      
      return {...prev,sub_content:data}
    })
        }}
        editorLoaded={editorLoaded}
        
      />
      </div>
      <div className="modal-footer">
        <button type="button" className="btn btn-secondary" data-bs-dismiss="modal" onClick={()=>hideModal("edit")}>Close</button>
        <button type="button" className="btn btn-primary" onClick={()=>{handleClose(postPart,"edit")}}>Save changes</button>
      </div>
    </div>
  </div>
</div>
{/* my modal end */}

 {/* my modal start */}
 <div className="modal fade" id="deletemodal" tabIndex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div className="modal-dialog  modal-dialog-scrollable modal-dialog-centered">
    <div className="modal-content">
      <div className="modal-header">
        <h1 className="modal-title fs-5" id="exampleModalLabel">Confirm Delete</h1>
        <button type="button" className="btn-close" data-bs-dismiss="modal" aria-label="Close" onClick={()=>hideModal("delete")}></button>
      </div>
      <div className="modal-body">
      {/* <input type="textarea" name="editor" id="editor"  value={postPart.sub_content} onChange={(event)=>handlePostPartChange(event)}/> */}
    
      confirm delete of the post : {postPart!=undefined && postPart.sub_title}
      </div>
      <div className="modal-footer">
        <button type="button" className="btn btn-secondary" data-bs-dismiss="modal" onClick={()=>hideModal("delete")}>Close</button>
        <button type="button" className="btn btn-primary" onClick={()=>{handleClose(postPart,"delete")}}>Delete</button>
      </div>
    </div>
  </div>
</div>
{/* my modal end */}


 {/* my modal start */}
 <div className="modal fade" id="createmodal" tabIndex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div className="modal-dialog  modal-dialog-scrollable modal-dialog-centered">
    <div className="modal-content">
      <div className="modal-header">
        <h1 className="modal-title fs-5" id="exampleModalLabel">Create Post</h1>
        <button type="button" className="btn-close" data-bs-dismiss="modal" aria-label="Close" onClick={()=>hideModal("create")}></button>
      </div>
      <div className="modal-body">
Enter Title <input type="text" 
 onChange={(event) => {
          setPostParts((prev)=>{
      
      return {...prev,sub_title:event.target.value}
    })
        }}
 />
 <br/>

      <Editor
        name="description"
        // value={postPart.sub_content}
        onChange={(data) => {
          setPostParts((prev)=>{
      
      return {...prev,sub_content:data}
    })
        }}
        editorLoaded={editorLoaded}
        
      />
     {/* d */}
      </div>
      <div className="modal-footer">
        <button type="button" className="btn btn-secondary" data-bs-dismiss="modal" onClick={()=>hideModal("create")}>Close</button>
        <button type="button" className="btn btn-primary" onClick={()=>{handleClose(postPart,"create")}}>Create</button>
      </div>
    </div>
  </div>
</div>
{/* my modal end */}
<div id="groupsmodal" class="modal fade" tabindex="-1">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">Manage groups</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"  onClick={()=>{
          const modal=document.getElementById("groupsmodal")
          modal.classList.remove("show");
          modal.classList.add("hide");
          modal.style.display="none";
        }
        }></button>
      </div>
      <div class="modal-body">
      <select size={groups===undefined ? 0:Object.keys(groups).length} multiple className="form-select" aria-label=" select example" onChange={(event)=>{
const val=event.target.value;
let list=Array.from(event.target.selectedOptions,option=>option.getAttribute('data-key'))
setSelectedGroup(list)
console.log(list)
  // handleGroupChange(list)
  }}>
{
  groups!=undefined && selectedGroup!=undefined &&  Array.prototype.map.call(groups, group => {
   
    if(selectedGroup.some(_group=>_group.name===group.name))return <option className="bg-primary" key={group.id} data-key={group.id} selected>{group.name}</option>
    if(!(selectedGroup.some(_group=>_group.name===group.name)))return <option className="bg-white"  key={group.id} data-key={group.id} >{group.name}</option>
  })
}
</select>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal" 
        onClick={()=>{
          const modal=document.getElementById("groupsmodal")
          modal.classList.remove("show");
          modal.classList.add("hide");
          modal.style.display="none";
        }
        }
        >Close</button>
        <button type="button" className="btn btn-secondary" onClick={(event)=>handleGroupsSave()}>Submit Groups</button>  

      </div>
    </div>
  </div>
</div>

{/* Groups moda end */}


  
    
    </div>
    
    </div>
   
    </>
    
    );
    }
    export default Main;
