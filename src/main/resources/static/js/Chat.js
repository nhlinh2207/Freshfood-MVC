class InteractiveChatbox {
    constructor(a, b) {
        this.args = {
            button: a,
            chatbox: b
        }
        this.state = false; 
    }

    display() {
        const {button, chatbox} = this.args;
        
        button.addEventListener('click', () => this.toggleState(chatbox))
    }

    toggleState(chatbox) {
        this.state = !this.state;
        this.showOrHideChatBox(chatbox, this.args.button);
    }

    showOrHideChatBox(chatbox, button) {
        if(this.state) {
            chatbox.classList.add('chatbox--active')
        } else if (!this.state) {
            chatbox.classList.remove('chatbox--active')
        }
    }
}

const chatButton = document.querySelector('.chatbox__button');
const chatContent = document.querySelector('.chatbox__support');
const chatbox = new InteractiveChatbox(chatButton, chatContent);
chatbox.display();