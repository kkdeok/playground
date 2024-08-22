package redis

type Client struct{}

func NewClient() *Client {
	return &Client{}
}

func (c Client) Get(key string) (string, error) {
	return "", nil
}
