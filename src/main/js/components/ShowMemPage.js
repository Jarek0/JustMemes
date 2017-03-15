import React from 'react';
import { Link } from "react-router";
import Mem from './Mem';
import Error from './Error';
import DownPanel from './DownPanel';


export default class ScroolMenu extends React.Component{

    constructor(props) {
        super(props);
        this.state={mem: [],id: 0,errorStatus: "",errorMessage: ""};
    }

    componentDidMount(){
        console.log("showOneMemPage");
        if(this.props.params.id===undefined){
            this.showMem(1);
            return;
        }

        this.showMem(this.props.params.id);
    }

    showMem(id){
        $.ajax({
            url: '/mem/'+id,
            type: 'GET',
            success: (function(data) {
                console.log(data);
                this.setState({ id: id });
                this.setState({ mem: data });
            }).bind(this),
            error: (function (xhr, ajaxOptions, thrownError) {
                this.setState({errorStatus: xhr.status});
                this.setState({errorMessage: xhr.responseText});
            }).bind(this)
        })

        window.scrollTo(0,0);
    }

    componentWillReceiveProps(nextProps) {
        if (nextProps.params.id !== this.props.params.id || nextProps.params.id !== undefined) {
            this.props.params.id=nextProps.params.id;
            this.showMemesFromPage(this.props.params.id);
        }
    }

    render(){
        if(this.state.errorMessage.length===0 && this.state.errorStatus.length===0){
            return(
                <div>
                    {
                     <Mem title={this.state.mem.title} key={'mem-'+this.state.mem.id} id={this.state.mem.id} fileType={this.state.mem.fileType} />
                    }
                    <DownPanel page="/" numberOfPage={0}/>
                </div>);
        }
        else{
            return(
                <div>
                    <Error status={this.state.errorStatus} mess={this.state.errorMessage}/>
                </div>);
        }


    }
}
